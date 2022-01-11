pipeline {
    agent
    {
        node {
                label 'master'
                customWorkspace "${env.JobPath}"
        }
    }
    stages
    {
        stage('Start') {
            steps {
                echo 'Hello from controller pipeline'
            }
        }
        stage ('Run sorry cypress dashboard') {
            steps {
                build job: 'Run sorry cypress dashboard', parameters: []
            }
        }

        stage('Verify_cypress_master') {
            steps {
                build job: 'Verify_cypress_master', parameters: []
            }
        }

        stage('Verify_cypress_myros_game') {
            steps {
                build job: 'Verify_cypress_myros_game', parameters: []
            }
        }

        stage('Run parallel tests on nodes') {
            parallel {
                stage('Group 1 - master') {
                    steps {
                        build job: 'Group 1 master', parameters: []
                    }
                }

                stage('Group 2 - myros-game') {
                    steps {
                        build job: 'Group_2_myros_game', parameters: []
                    }
                }

                stage('Group 3 - master') {
                    steps {
                        build job: 'Group_3_master', parameters: []
                    }
                }

                stage('Group 4 - myros-game') {
                    steps {
                        build job: 'Group_4_myros_game', parameters: []
                    }
                }

                stage('Group 5 - master') {
                    steps {
                        build job: 'Group_5_master', parameters: []
                    }
                }
            }
        }
        stage('End') {
            steps {
                echo 'Bye'
            }
        }
    }
}
