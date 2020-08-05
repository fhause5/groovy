
package devops.jenkins



class globalVariables {
    void test () {
        println("test")
    }
    void DB_SID() {
        DB_SID='TRDSRV2'
        return DB_SID
    }
    void DB_ENDPOINT() {
        DB_ENDPOINT='real1.eu-west-1.rds.amazonaws.com'
        return DB_ENDPOINT
    }
    void DB_PORT() {
        DB_PORT='1521'
        return DB_PORT
    }
    void DB_PROTOCOL() {
        DB_PROTOCOL='tcp'
        return DB_PROTOCOL
    }
}


