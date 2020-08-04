import groovy.json.JsonSlurperClassic
def call(url, jobName, cred) {
    try {
        String auth = cred.bytes.encodeBase64().toString()
        def response =  httpRequest url: "${url}/job/${jobName}/lastBuild/api/json",
                httpMode: 'GET',
                customHeaders: [[name: 'Authorization', value: "Basic ${auth}"]],
                validResponseCodes: '100:499'
        return new JsonSlurperClassic()
                .parseText(response.content).result
    } catch(Exception e) {
        println("Unvalid parameters")
    }
}
