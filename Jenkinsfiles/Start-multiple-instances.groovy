pipeline {
    agent {
        node {
            label 'master'
        }
    }
    parameters {
        choice(
                choices: 'start\nstop',
                description: 'PLEASE select start or stop instances',
                name: 'exec'
        )
        choice(
                choices: '1\n2\n3',
                description: 'Select 2 or 3 nodes start',
                name: 'count'
        )


    }
    stages {
        stage("AWS-START-1-nodes") {
            when{ expression { params.exec == 'start' && params.count == '1' }}
            steps {
                script {
                    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'aws-key', usernameVariable: 'AWS_ACCESS_KEY_ID', passwordVariable: 'AWS_SECRET_ACCESS_KEY']]) {
                        println("Start instances")
                        sh "aws ec2 start-instances --region eu-central-1 --instance-ids i-02e34f1ca936d99d2"
                    }
                }
            }
        }        
        stage("AWS-START-2-nodes") {
            when{ expression { params.exec == 'start' && params.count == '2' }}
            steps {
                script {
                    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'aws-key', usernameVariable: 'AWS_ACCESS_KEY_ID', passwordVariable: 'AWS_SECRET_ACCESS_KEY']]) {
                        println("Start instances")
                        sh "aws ec2 start-instances --region eu-central-1 --instance-ids i-02e37f34936d99d2"

                    }
                }
            }
        }
        stage("AWS-START-3-nodes") {
            when{ expression { params.exec == 'start' &&  params.count == '3'}}
            steps {
                script {
                    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'aws-key', usernameVariable: 'AWS_ACCESS_KEY_ID', passwordVariable: 'AWS_SECRET_ACCESS_KEY']]) {
                        println("Start instances")
                        sh "aws ec2 start-instances --region eu-central-1 --instance-ids i-02e3341ca936d99d2"
                        sh "aws ec2 start-instances --region eu-central-1 --instance-ids i-0101d7034be5ca200"
                    }
                }
            }
        }
        stage("AWS-STOP-1-nodes") {
            when{ expression { params.exec == 'stop' &&  params.count == '1'}}
            steps {
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'aws-key', usernameVariable: 'AWS_ACCESS_KEY_ID', passwordVariable: 'AWS_SECRET_ACCESS_KEY']]) {
                    println("Stop instance")
                    sh "aws ec2 stop-instances --region eu-central-1 --instance-ids i-02e37f34346d99d2"
                }
            }
        }
        
        stage("AWS-STOP-2-nodes") {
            when{ expression { params.exec == 'stop' &&  params.count == '2'}}
            steps {
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'aws-key', usernameVariable: 'AWS_ACCESS_KEY_ID', passwordVariable: 'AWS_SECRET_ACCESS_KEY']]) {
                    println("Stop instance")
                    sh "aws ec2 stop-instances --region eu-central-1 --instance-ids i-02e37f34936d99d2"
                }
            }
        }
        stage("AWS-STOP-3-nodes") {
            when{ expression { params.exec == 'stop' &&  params.count == '3'}}
            steps {
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'aws-key', usernameVariable: 'AWS_ACCESS_KEY_ID', passwordVariable: 'AWS_SECRET_ACCESS_KEY']]) {
                    println("Stop instance")
                    sh "aws ec2 stop-instances --region eu-central-1 --instance-ids i-02e37f1c346d99d2"
                    sh "aws ec2 stop-instances --region eu-central-1 --instance-ids i-010344345ca200"
                }
            }
        }
    }
}

