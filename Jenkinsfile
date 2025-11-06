pipeline {
    agent any
 
    tools {
        maven 'MAVEN_HOME'
    }
 
    options {
        ansiColor('xterm')   // ✅ Enable colored console output
    }
 
    environment {
        PROJECT_NAME = "Bank Account CI"
        SLACK_CHANNEL = "#ci-notifications"   // Simulation only
    }
 
    stages {
 
        stage('Checkout') {
            steps {
                echo "\u001B[36m📦 Checking out project...\u001B[0m"
                git branch: 'EnchancedBankAccount', url: 'https://github.com/P123671/bank-account-ci.git'
            }
        }
 
        stage('Build') {
            steps {
                echo "\u001B[34m⚙️ Building ${env.PROJECT_NAME}...\u001B[0m"
                sh 'mvn clean compile'
            }
        }
 
        stage('Test & Coverage') {
            steps {
                echo "\u001B[33m🧪 Running tests...\u001B[0m"
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
 
        stage('Package') {
            steps {
                echo "\u001B[35m📦 Packaging artifact...\u001B[0m"
                sh 'mvn package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
 
    post {
 
        success {
            echo ""
            echo "\u001B[32m✅✅✅ SUCCESS NOTIFICATION ✅✅✅\u001B[0m"
            echo "\u001B[32m🎉 ${env.PROJECT_NAME} build #${env.BUILD_NUMBER} succeeded!\u001B[0m"
            echo "\u001B[32m🔗 View details: ${env.BUILD_URL}\u001B[0m"
            echo ""
        }
 
        unstable {
            echo "\u001B[33m⚠️ Build marked as UNSTABLE (checkstyle or low coverage)\u001B[0m"
        }
 
        failure {
            echo ""
            echo "\u001B[31m❌❌❌ FAILURE NOTIFICATION ❌❌❌\u001B[0m"
            echo "\u001B[31m💥 ${env.PROJECT_NAME} build #${env.BUILD_NUMBER} failed!\u001B[0m"
            echo "\u001B[31m🔗 Logs: ${env.BUILD_URL}\u001B[0m"
            echo ""
        }
 
        always {
            echo "\u001B[36m📊 Pipeline completed at ${new Date()}\u001B[0m"
        }
    }
}