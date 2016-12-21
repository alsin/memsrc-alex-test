package memsrc.alex.test


import grails.rest.*
import grails.converters.*
import grails.plugins.rest.client.*

import org.grails.web.json.JSONObject

class ProjectsController {
    ConfigService configService
    MemsourceApiService memsourceApiService


    static responseFormats = ['json', 'xml', 'html']

    def index() {
        def config = configService.getMemsourceAccountConfig()
        if (!config) {
            config = configService.modifyMemsourceAccountConfig("alex_xela", "memsource", "")
            assert config != null
//            [errorCode: "NotLoggedIn"]
        }
        if (!config.memsourceSessionToken) {
            log.info("Unauthorized, need to log in first..")
            def loginResponseJson = memsourceApiService.login()
            def token = loginResponseJson.token
            log.info("Successfully logged in! Saving token: ${token}")
            configService.modifyMemsourceSessionToken(token)
        }

        def projectsJson = memsourceApiService.listProjects()

        def errCode = projectsJson.errorCode
        if (errCode == configService.getUnAuthErrorCode()) {
            log.info("Unauthorized, probably token expired. Need to re-log in first..")
            def loginResponseJson = memsourceApiService.login()
            def token = loginResponseJson.token
            log.info("Successfully logged in! Token: ${token}")
            projectsJson = memsourceApiService.listProjects()
            if (projectsJson.errorCode) {
                throw new Exception("Getting projects failed: ${projectsJson}")
            }
        }

        respond projectsJson
    }

    def list() {
        respond {code: 'OK'}
    }

}
