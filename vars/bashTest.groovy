def call(text) {
    try {
        sh "echo ${text} "
    } catch(Exception e) {
        echo "does not work"
        echo "The build is failed"
        currentBuild.result = 'FAILURE'
    }
}
