@Library('github.com/fhause5/groovy') _


pipeline {
    agent any

//    parameters {
//        choice(choices: "prod\nany", description: '', name: 'ENVIRONMENT')
//    }
    stages {
        stage('STAGE 1') {
            steps {
                node("master") {
                    script {
                        sh 'echo 1'
                        sh 'echo 2'
                    }
                }
            }
        }
    }
}
