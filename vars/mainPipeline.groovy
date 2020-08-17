import src.devops.jenkins.globalVariables;

test = ConfigParser()
def call() {
    def context = [:]
    node("master") {
        stage("Stage 1") {
          try {
            sh '''
            echo Stage 1
            '''
          }
          catch (Exception ex) {

            println("stage1")
          }
        }
        stage("Stage 2") {
          try {
            sh '''
            echo Stage 2
            '''
          }
          catch (Exception ex) {

            println("stage2")
          }
        }        
    }
}
