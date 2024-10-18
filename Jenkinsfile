pipeline {
    agent any

    environment {
        MAVEN_HOME = '/opt/apache-maven-3.9.9'
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk'
        PATH = "$MAVEN_HOME/bin:$JAVA_HOME/bin:$PATH"
        RECIPIENT_EMAIL = "your.email@gmail.com"
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Checkout code from your repository
                git 'https://github.com/niyazhashmi1105/playwright-java.git'
            }
        }

        stage('Install Playwright Browsers') {
            steps {
                sh 'mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"'
            }
        }

        stage('Build and Test') {
            steps {
                // Clean and run the tests
                sh 'mvn clean test'
            }
        }

        stage('Fetch Reports') {
            steps {
                script {
                    // Check if the report exists and copy it to the workspace
                    def reportPath = "reports/index.html"
                    if (fileExists(reportPath)) {
                        archiveArtifacts artifacts: reportPath, allowEmptyArchive: false
                    } else {
                        error "Report not found: ${reportPath}"
                    }
                }
            }
        }
    }

    post {
            success {
                script {
                    echo "Build succeeded. Sending success email..."
                    emailext to: "${RECIPIENT_EMAIL}",
                        subject: "Build SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                        body: """
                        <p>Good news!</p>
                        <p>The build <b>${env.JOB_NAME} #${env.BUILD_NUMBER}</b> was successful!</p>
                        <p>Find the test report at: <a href="${env.BUILD_URL}artifact/reports/index.html">Test Report</a></p>
                        """
                }
            }

            failure {
                script {
                    echo "Build failed. Sending failure email..."
                    emailext to: "${RECIPIENT_EMAIL}",
                        subject: "Build FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                        body: """
                        <p>Unfortunately, the build <b>${env.JOB_NAME} #${env.BUILD_NUMBER}</b> failed.</p>
                        <p>Please check the console output and investigate the issue.</p>
                        <p>You can find the test report (if generated) here: <a href="${env.BUILD_URL}artifact/reports/index.html">Test Report</a></p>
                        """
                }
            }
        }
}
