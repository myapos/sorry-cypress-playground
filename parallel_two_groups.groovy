pipeline {
    agent none
    tools { nodejs 'Node 12.16.3' }
    stages {
        stage('Parallel') {
            parallel {
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

                stage('Verify cypress - master') {
                    agent {
                        label 'master'
                    }

                    steps {
                        sh 'npm run cy:verify'
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

                stage('Run tests of group 1 - master') {
                    agent {
                        label 'master'
                    }

                    steps {
                        sh 'npm run cy:parallel:group_1'
                    }
                }

                stage('Runs On myros-game') {
                    agent {
                        label 'myros-game'
                    }
                    steps {
                        sh "echo 'Hello from myros-game'"
                    }
                }

                stage('Cloning Git - myros-game') {
                    agent {
                        label 'myros-game'
                    }

                    steps {
                        git 'https://github.com/myapos/sorry-cypress-playground'
                    }
                }

                stage('Install dependencies - myros-game') {
                    agent {
                        label 'myros-game'
                    }

                    steps {
                        sh 'npm install'
                    }
                }

                stage('Verify cypress - myros-game') {
                    agent {
                        label 'myros-game'
                    }

                    steps {
                        sh 'npm run cy:verify'
                    }
                }

                stage('Run tests of group 2 - myros-game') {
                    agent {
                        label 'myros-game'
                    }

                    steps {
                        sh 'npm run cy:parallel:group_2'
                    }
                }
            }
        }
    }
}
