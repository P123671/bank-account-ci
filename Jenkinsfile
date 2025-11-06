pipeline {
    agent any
    tools {
        maven 'MAVEN_HOME'
    }
 
    environment {
        EMAIL_RECIPIENTS = 'student@example.com'
    }
 
    stages {
        stage('Checkout') {
            steps {
                git branch: 'EnchancedBankAccount', url: 'https://github.com/P123671/bank-account-ci.git'
            }
        }
 
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
 
        stage('Test & Coverage') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    jacoco(
                        execPattern: '**/target/jacoco.exec',
                        classPattern: '**/target/classes',
                        sourcePattern: '**/src/main/java'
                    )
                }
            }
        }
 
        stage('Package & Archive') {
            steps {
                sh 'mvn package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
 
    post {
        success {
            echo '✅ Build and tests passed!'
            emailext(
                to: "${EMAIL_RECIPIENTS}",
                subject: "✅ Jenkins Build Successful: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "Build succeeded. Check details at ${env.BUILD_URL}"
            )
        }
        failure {
            echo '❌ Build failed!'
            emailext(
                to: "${EMAIL_RECIPIENTS}",
                subject: "❌ Jenkins Build Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "Build failed. See logs at ${env.BUILD_URL}"
            )
        }
        always {
            echo "Pipeline completed. Logs and artifacts archived."
        }
    }
}
