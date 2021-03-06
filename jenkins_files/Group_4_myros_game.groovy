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

        stage('Group 4 - myros-game') {
            steps {
                sh 'npm run cy:parallel:group_4'
            }
        }
    }
}
