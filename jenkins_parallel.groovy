stage('Cypress') {
    tools { nodejs 'Node 12.16.3' }

    stages {
        stage('Cloning Git') {
            steps {
                git 'https://github.com/myapos/sorry-cypress-playground'
            }
        }

        stage('Install dependencies') {
            steps {
                sh 'npm install'
            }
        }

        stage('Verify cypress') {
            steps {
                sh 'npm run cy:verify'
            }
        }

        stage('Run minio') {
            steps {
                sh 'echo jenkins | sudo -S docker-compose -f ./docker-compose.minio.yml up -d'
            }
        }

        stage('Run tests of group 1 on two node') {
            parallel {
                stage('Group 1') {
                    agent {
                        node {
                            label 'master'
                        }
                    }
                    steps {
                        sh 'npm run cy:parallel:group_1'
                    }
                }

                stage('Group 2') {
                    agent {
                        node {
                            label 'myros_game'
                        }
                    }
                    steps {
                        sh 'npm run cy:parallel:group_2'
                    }
                }
            }
        }
    }
}
