pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    stages {
        stage('Build') {
            steps {
                script {
                    env.JAVA_HOME = tool name: 'JDK 17'
                }
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Allure Report') {
            steps {
                sh 'mvn allure:report'
            }
        }
    }
    post {
        always {
            allure includeProperties: false, jdk: 'JDK 17'
        }
    }
}
