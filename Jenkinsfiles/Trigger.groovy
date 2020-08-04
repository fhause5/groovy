pipeline {			
    agent any			 
    stages {
        stage ("Triger the Jobs") {		
            steps {
                script {
                    try {
                        invoke = ["ihor-test-vars"]
                        invoke.each {
                            build job: it, parameters: [string(name: 'Trigger', value: "true")]
                        }
                    } catch (Exception e) {
                        currentBuild.result = 'SUCCESS'
                    }
                }
            }
        }
    }
}
