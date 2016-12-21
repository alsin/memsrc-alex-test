package memsrc.alex.test

class Config {
    String memsourceAccountLogin
    String memsourceAccountPassword
    String memsourceSessionToken

    static constraints = {
        memsourceAccountLogin nullable: true
        memsourceAccountPassword nullable: true
        memsourceSessionToken nullable: true
    }

}
