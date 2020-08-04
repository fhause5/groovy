def call() {
    def context = [:]
    node("master") {
        stage("Clean") {
          try {
            sh '''
            find /var/lib/jenkins/jobs/*-cd-pipeline  -name workspace -type d
            find /var/lib/jenkins/jobs/*-cd-pipeline/jobs  -name workspace -type d -prune -exec rm -rf '{}' '+'
            '''
          }
          catch (Exception ex) {

            println("There are not such jobs")
          }
        }
    }
}
