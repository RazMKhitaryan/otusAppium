pipeline {
    agent { label 'maven' }

    stages {
        stage('Test Allure CLI') {
            steps {
                sh "allure --version"
            }
        }

        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM',
                          branches: [[name: 'main']],
                          userRemoteConfigs: [[url: 'https://github.com/RazMKhitaryan/otusAppium.git']]
                ])
            }
        }

        stage('Run Tests') {
            steps {
                sh "mvn clean test -DrunType=remote"
            }
        }
    }

    post {
        always {
            echo "Publishing Allure results..."
            allure([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'allure-results']]
            ])
            echo "Pipeline finished"
        }
    }
}
