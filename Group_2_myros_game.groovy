pipeline {
    agent {
        node {
            label 'myros-game'
        }
    }
    tools { nodejs 'Node 12.16.3' }
    stages {
        stage('Runs On myros-game') {
            steps {
                sh "echo 'Hello from myros-game'"
            }
        }

        stage('Cloning Git - myros-game') {
            steps {
                git 'https://github.com/myapos/sorry-cypress-playground'
            }
        }

        stage('Install dependencies - myros-game') {
            steps {
                sh 'npm install'
            }
        }

        stage('Verify cypress') {
            steps {
                sh 'npm run cy:verify'
            }
        }

        stage('Group 2 - myros-game') {
            steps {
                sh 'npm run cy:parallel:group_2'
            }
        }
    }
}
