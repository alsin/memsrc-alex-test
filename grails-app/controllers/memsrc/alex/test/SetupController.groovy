package memsrc.alex.test


import grails.rest.*
import grails.converters.*

/**
 * Controller for Setup page.
 */
class SetupController {
	static responseFormats = ['json', 'xml']

    ConfigService configService

    def index() {
        def account = configService.getMemsourceAccountConfig()
        render(view: "/config", model: [userName: account ? account.memsourceAccountLogin : null])
    }

    /**
     * Modifies Memsource account credentials - login and password.
     *
     * @return
     */
    def modify() {
        def login = params.login
        def password = params.password
        log.info("SetupController.modify() called with params: ${params}")
        configService.modifyMemsourceAccountLogin(login, password)
        log.info("Memsource credentials updated!")
        render([status: "OK"] as JSON)
    }

    def handleException(Exception e) {
        log.error("Exception occurred in setup controller: ${e.getMessage()}")
        response.status = 500
        render([status: "FAILED", errorCode: "ConfigFailure", errorDescription: e.getMessage()] as JSON)
    }

}
