pipeline {
    agent none
    tools { nodejs 'Node 12.16.3' }
    stages {
        stage('Runs On master') {
            agent {
                label 'master'
            }
            steps {
                sh "echo 'Hello from master'"
            }
        }

        stage('Cloning Git - master') {
            agent {
                label 'master'
            }

            steps {
                git 'https://github.com/myapos/sorry-cypress-playground'
            }
        }

        stage('Install dependencies - master') {
            agent {
                label 'master'
            }

            steps {
                sh 'npm install'
            }
        }

        stage('Kill previous sorry cypress dashboard') {
            agent {
                label 'master'
            }

            steps {
                sh 'docker kill $(docker ps -q)'
            }
        }

        stage('Run minio - master') {
            agent {
                label 'master'
            }

            steps {
                sh 'echo jenkins | sudo -S docker-compose -f ./docker-compose.minio.yml up -d'
            }
        }
    }
}
