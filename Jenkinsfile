pipeline {
    agent any

    tools {
        jdk 'JDK17'       // Ensure this matches the JDK name in Jenkins Global Tool Configuration
        maven 'Maven3'    // Ensure this matches the Maven name in Jenkins Global Tool Configuration
    }

    environment {
        TESTNG_XML = 'testng.xml'  // Path to your TestNG suite file
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Cloning repository...'
                git branch: 'main', url: 'https://github.com/ChaitanyaVK-AutoQA/RestAssured'
            }
        }

        stage('Install Dependencies') {
            steps {
                echo 'Installing dependencies...'
                bat 'mvn clean install'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Executing tests...'
                bat "mvn test -DsuiteXmlFile=${env.TESTNG_XML}"
            }
        }

        stage('Generate Reports') {
            steps {
                echo 'Generating reports...'
                bat 'mvn surefire-report:report'
            }
        }
    }

    post {
        always {
            echo 'Archiving test results...'
            junit '**/target/surefire-reports/*.xml'
        }

        success {
            echo 'Build and tests completed successfully!'
        }

        failure {
            echo 'Build or tests failed.'
        }
    }
}
