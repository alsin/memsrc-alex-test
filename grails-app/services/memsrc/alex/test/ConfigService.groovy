package memsrc.alex.test

import grails.transaction.Transactional
import grails.core.*
/**
 * Service for reading and modifying Memsource account configuration.
 *
 */
@Transactional
class ConfigService {
    GrailsApplication grailsApplication

    def getMemsourceAccountConfig() {
        return Config.read(1)
    }

    def modifyMemsourceAccountLogin(String login) {
        return modifyMemsourceAccountConfig([memsourceAccountLogin: login])
    }

    def modifyMemsourceAccountPassword(String password) {
        return modifyMemsourceAccountConfig([memsourceAccountPassword: password])
    }

    def modifyMemsourceSessionToken(String token) {
        return modifyMemsourceAccountConfig([memsourceSessionToken: token])
    }

    def modifyMemsourceAccountLogin(String login, String password) {
        return modifyMemsourceAccountConfig([memsourceAccountLogin: login,
                                             memsourceAccountPassword: password])
    }

    def modifyMemsourceAccountConfig(Map props) {
        def config = Config.get(1)
        if (!config) {
            config = new Config(props)
        }  else {
            config.properties = props
        }

        config.save()
        return config
    }

    def getUnAuthErrorCode() {
        return getPropValue('memsource.unAuthErrCode')
    }

    def getMemsourceLoginAPIUrl() {
        return compoundMemsourceApiUrl(getPropValue('memsource.loginApiUrl'))
    }

    def getMemsourceProjectsListAPIUrl() {
        return compoundMemsourceApiUrl(getPropValue('memsource.projectsListApiUrl'))
    }

    def compoundMemsourceApiUrl(String uri) {
        return getPropValue('memsource.apiBaseUrl') + uri
    }

    def getPropValue(String propName, boolean failIfNotFound = true) {
        def val = grailsApplication.config.getProperty(propName)
        if (!val && failIfNotFound) {
            throw new Exception("No property named '${propName}' found in app configuration!")
        }
        return val
    }
}
