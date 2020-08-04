def call(chatid, botid, message) {
    try {
        String text = "{ \"chat_id\": \"${chatid}\", \"text\": \"${message}\"}"
        httpRequest url: "https://api.telegram.org/bot${botid}/sendMessage",
                httpMode: 'POST',
                contentType: 'APPLICATION_JSON',
                requestBody: text,
                consoleLogResponseBody: true
        validResponseCodes: '100:499'
    } catch (Exception e) {
        println("Please input correct parameters")
    }
}
