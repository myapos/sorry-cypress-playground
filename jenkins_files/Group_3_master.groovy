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

        stage('Group 3 - master') {
            steps {
                sh 'npm run cy:parallel:group_3'
            }
        }
    }
}
