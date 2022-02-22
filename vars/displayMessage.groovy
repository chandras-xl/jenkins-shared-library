#!groovy

def call(String msg){
    println("Hello! $msg")

    pipeline{
        agent any 

        stages{
            stage('build'){
                sh '''
                    echo "Building the pipeline"
                '''
            }
            stage('Test'){
                sh '''
                    echo "Performing Unit test"
                '''
            }
            stage('Deploy'){
                sh '''
                    echo "Deploying"
                '''
            }
        }
        post{
            success{
                slackSend channel: '#jenkinsci',
                          color: 'good',
                          message: "Executed the job ${currentBuild.fullDisplayName}"       
            }
            failure{
                slackSend channel: '#jenkinsci',
                          color: 'danger',
                          message: "Failed to execute the job ${currentBuild.fullDisplayName}"
            }
        }
    }
}