pipeline {
    agent any

    tools {
        jdk 'JDK17'       // Ensure this matches the JDK name in Jenkins Global Tool Configuration
        maven 'Maven3'    // Ensure this matches the Maven name in Jenkins Global Tool Configuration
    }

    parameters {
        choice(
            name: 'BRANCH_NAME',
            choices: ['main', 'develop', 'release', 'feature'],
            description: 'Select the Git branch to build from'
        )

        string(
            name: 'TESTNG_XML',
            defaultValue: 'testng.xml',
            description: 'TestNG suite XML file to run'
        )
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Cloning repository from branch: ${params.BRANCH_NAME}"
                git branch: "${params.BRANCH_NAME}", url: 'https://github.com/ChaitanyaVK-AutoQA/RestAssured'
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
                echo "Executing tests with suite: ${params.TESTNG_XML}"
                bat "mvn test -DsuiteXmlFile=${params.TESTNG_XML}"
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
