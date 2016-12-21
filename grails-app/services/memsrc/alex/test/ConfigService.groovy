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
        def config = Config.get(1)
        if (!config) {
            config = new Config(memsourceAccountLogin: login)
        }  else {
            config.setMemsourceAccountLogin(login)
        }
        config.save()
    }

    def modifyMemsourceAccountPassword(String password) {
        def config = Config.get(1)
        if (!config) {
            config = new Config(memsourceAccountPassword: password)
        }  else {
            config.setMemsourceAccountPassword(password)
        }
        config.save()
    }

    def modifyMemsourceSessionToken(String token) {
        def config = Config.get(1)
        if (!config) {
            config = new Config(memsourceSessionToken: token)
        }  else {
            config.setMemsourceSessionToken(token)
        }
        config.save()
    }

    def modifyMemsourceAccountConfig(String login, String password, String token) {
        def config = Config.get(1)
        if (!config) {
            config = new Config(memsourceAccountLogin: login, memsourceAccountPassword: password,
                    memsourceSessionToken: token)
        }  else {
            config.setMemsourceAccountLogin(login)
            config.setMemsourceAccountPassword(password)
            config.setMemsourceSessionToken(token)
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
