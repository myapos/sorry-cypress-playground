pipeline {
    agent {
        node {
            label 'master'
        }
    }
    tools { nodejs 'Node 12.16.3' }
    stages {
        stage('Runs On master') {
            steps {
                sh "echo 'Hello from master'"
            }
        }

        stage('Cloning Git - master') {
            steps {
                git 'https://github.com/myapos/sorry-cypress-playground'
            }
        }

        stage('Install dependencies - master') {
            steps {
                sh 'npm install'
            }
        }

        stage('Verify cypress master') {
            steps {
                sh 'npm run cy:verify'
            }
        }
    }
}
