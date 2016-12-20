package memsource.alex

import grails.transaction.Transactional
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import grails.plugins.rest.client.*

@Transactional
class MemsourceApiService {

    ConfigService configService

    def listProjects() {
        def config = configService.getMemsourceAccountConfig()
        def url = configService.getMemsourceProjectsListAPIUrl()
        log.info("Getting projects from Memsource using project list API URL: ${url}")

        def rest = new RestBuilder()
        def response = rest.post(url) {
            contentType "multipart/form-data"
            token = config ? config.memsourceSessionToken : ""
        }
        return validateResponse(response)
    }

    def login() {
        def config = configService.getMemsourceAccountConfig()
        def url = configService.getMemsourceLoginAPIUrl()
        log.info("Logging into Memsource using API URL: ${url}")

        def rest = new RestBuilder()
        def response = rest.post(url) {
            contentType "multipart/form-data"
            userName = config.memsourceAccountLogin
            password = config.memsourceAccountPassword
        }
        return validateResponse(response)
    }

    def validateResponse(response) {
        log.info("Validating response: ${response.getBody()}")
        if (!response) {
            throw new Exception("Unexpected null response received!")
        }

        if (!(response.json instanceof JSONObject) && !(response.json instanceof JSONArray)) {
            throw new Exception("Expected json or jsonarray response, but received something else: ${response}")
        }

        if ((response.json instanceof JSONObject) && (response.json.errorCode || response.json.errorDescription)) {
            throw new Exception("Got an error json response: errorCode=${response.json.errorCode}, " +
                    "errorDescription=${response.json.errorDescription}")
        }
        return response.json
    }
}
