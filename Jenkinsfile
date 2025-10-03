pipeline {
    agent any // Specifies where the pipeline will run (e.g., on any available agent)

    stages { // Defines the different stages of the pipeline
        stage('Build') { // A specific stage named 'Build'
            steps { // Actions to be performed in this stage
                echo 'Building the application...' // Example command
                // Add your build commands here, e.g., mvn clean install
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                script{
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        // Add your test commands here, e.g., mvn test
                        git 'https://github.com/madhusmitaprusty90/pomPlaywrightJavaAutomation'
                        sh "mvn clean test -Dsurefire.suiteXmlFiles=testrunners/testng.xml"
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying the application...'
                // Add your deployment commands here
            }
        }
    }

    post { // Actions to perform after the pipeline completes, regardless of success or failure
        always {
            echo 'Pipeline finished.'
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}