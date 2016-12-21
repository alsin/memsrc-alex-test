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
        render(view: "config")
    }

    /**
     * Modifies Memsource account credentials - login and password.
     *
     * @return
     */
    def modifyMemsourceCredentials() {
        def login = params.login
        def password = params.password

        configService.modifyConfig(login, password)
    }

}
