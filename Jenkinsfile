pipeline {
    agent any
    tools {
        maven 'MAVEN_HOME'
    }
 
    environment {
        PROJECT_NAME = "Bank Account CI"
        SLACK_CHANNEL = "#ci-notifications"   // just for display simulation
    }
 
    stages {
        stage('Checkout') {
            steps {
                echo "📦 Checking out project..."
                git branch: 'EnchancedBankAccount', url: 'https://github.com/P123671/bank-account-ci.git'
            }
        }
 
        stage('Build') {
            steps {
                echo "⚙️ Building ${env.PROJECT_NAME}..."
                sh 'mvn clean compile'
            }
        }
 
        stage('Test & Coverage') {
            steps {
                echo "🧪 Running tests..."
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
                echo "📦 Packaging artifact..."
                sh 'mvn package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
 
    post {
        success {
            echo ""
            echo "✅✅✅ SUCCESS NOTIFICATION ✅✅✅"
            echo "Message to ${env.SLACK_CHANNEL}:"
            echo "🎉 ${env.PROJECT_NAME} build #${env.BUILD_NUMBER} succeeded!"
            echo "🔗 View details: ${env.BUILD_URL}"
            echo ""
        }
        unstable {
            echo ""
            echo "⚠️ WARNING: Build marked as UNSTABLE (Checkstyle or coverage warnings)."
            echo ""
        }
        failure {
            echo ""
            echo "❌❌❌ FAILURE NOTIFICATION ❌❌❌"
            echo "Message to ${env.SLACK_CHANNEL}:"
            echo "💥 ${env.PROJECT_NAME} build #${env.BUILD_NUMBER} failed!"
            echo "🔗 Logs: ${env.BUILD_URL}"
            echo ""
        }
        always {
            echo "📊 Pipeline completed at ${new Date()}"
        }
    }
}
