pipeline {
    agent any

    environment {
        MAVEN_HOME = '/opt/homebrew/Cellar/maven/3.9.9/libexec'
        JAVA_HOME = '/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home'
        PATH = "$MAVEN_HOME/bin:$JAVA_HOME/bin:$PATH"
        RECIPIENT_EMAIL = 'niyazhashmi161921@gmail.com'
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Checkout code from your repository
                git branch: 'main', credentialsId: '9fb3157c-8eb6-4079-9ff5-7c4d6838a9be', url: 'https://github.com/niyazhashmi1105/playwright-java.git'
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
                sh 'mvn clean test -Pbrowserstack -Denv=browserstack'
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
                    echo "Build succeeded. Sending success email..."
                    emailext attachmentsPattern: 'reports/index.html',
                    body: '''<p>Good news!</p>
                             <p>The build <b>${env.JOB_NAME} ${env.BUILD_NUMBER}</b> was successful!</p>
                             <p>Find the test report at: <a href='${env.BUILD_URL}artifact/reports/index.html'>Automation Report</a></p>''',
                             mimeType: 'text/html',
                             subject: 'Build SUCCESS: ${env.JOB_NAME} ${env.BUILD_NUMBER}',
                             to: 'hashmimdniyaz@gmail.com'

                }
        }
}
