pipeline {
    agent any
    tools {
        maven 'MAVEN_HOME'
    }
 
    stages {
 
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/P123671/bank-account-ci.git'
            }
        }
 
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
 
        stage('Run Unit & Integration Tests') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
 
                    // ✅ Publish JaCoCo coverage in Jenkins
                    jacoco(
                        execPattern: '**/target/jacoco.exec',
                        classPattern: '**/target/classes',
                        sourcePattern: '**/src/main/java',
                        inclusionPattern: '**/*.class'
                    )
                }
                failure {
                    echo '❌ Tests failed! Check the “Test Result” tab.'
                }
            }
        }
 
        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
 
    post {
        success {
            echo '✅ Build, tests, and coverage all passed!'
        }
        failure {
            echo '💥 Pipeline failed. Check the failed stage.'
        }
    }
}
