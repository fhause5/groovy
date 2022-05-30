def jenkinsSlaveImage = "jenkins:slave"

pipeline {
    agent {
        kubernetes {
            label "ocas-build-${UUID.randomUUID().toString()}"
            inheritFrom 'dind'
            cloud "${env.BARNYARD_RUNNER}"

            containerTemplate {
                name 'ocas-build'
                image "$jenkinsSlaveImage"
                ttyEnabled true
                alwaysPullImage true
            }
        }
    }
    parameters {
        choice(name: 'activity_type', choices: ['','create_branch', 'delete_branch', 'create_MR', 'create_project', 'merge_MR', 'reverse_merge'], description: 'Select type of activity')
    options {
        timestamps()
        disableConcurrentBuilds()
    }
    stages {
        stage('Install Requirements') {
            parallel {
                stage ("Job1") {
                    steps {
                        script {
                            echo "Build triggered by: 1"
                            sh "sleep 4s"
                            
                        }
                    }
                }
                stage ("Job2") {
                    steps {
                        script {
                            //sh  "export http_proxy='' && export https_proxy=www-proxy.us.oracle.com:80 && pip3.7 --trusted-host ${env.PYPI_HOST} install -r requirements.txt"
                            echo "Build triggered by: 2"
                            sh "sleep 2s"
                        }
                    }
                }
            }
        }
    }
}

def createBuilds(stageName){
    stage(stageName) {
        echo "$stageName"
        sh """ python3.7 -u team-utilities.py ${params.activity_type} ${params.group} ${params.branch_name} ${params.ref_branch} ${params.project_name} """
    }
}
