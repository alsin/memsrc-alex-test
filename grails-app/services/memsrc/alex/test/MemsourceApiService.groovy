package memsrc.alex.test

import grails.transaction.Transactional
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import grails.plugins.rest.client.*
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

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
        def resp = rest.post(url) {
            contentType "multipart/form-data"
            userName = config.memsourceAccountLogin
            password = config.memsourceAccountPassword
        }

//        def client = new groovyx.net.http.RESTClient('myRestFulURL')
//
//        def json = client.get(contentType: JSON)
//        net.sf.json.JSON jsonData = json.data as net.sf.json.JSON
//        def slurper = new JsonSlurper().parseText(jsonData)
//
//        def client = new groovyx.net.http.HTTPBuilder('myRestFulURL')
//        client.setHeaders(Accept: 'application/json')
//
//        def json = client.get(contentType: TEXT)
//        def slurper = new groovy.json.JsonSlurper().parse(json)
        return validateResponse(resp)
    }

    def validateResponse(response) {
        log.info("Validating Memsource API response:")
//        log.info(" - Memsource response.responseEntity = ${response.responseEntity}")
//        log.info(" - Memsource response.json = ${response.json}")
//        log.info(" - Memsource response.text = ${response.text}")
//        log.info(" - Memsource response.xml = ${response.xml}")
//        log.info(" - Memsource response.getStatus() = ${response.getStatus()}")

        if (!response) {
            throw new Exception("Unexpected null response received!")
        }

        if (response.getStatus() == 401) {
            throw new Exception("Unauthorized! Please, check your Memsource credentials setup.")
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
