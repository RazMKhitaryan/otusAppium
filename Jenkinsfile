import groovy.json.JsonSlurper

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

            sh 'allure generate --clean allure-results'
            script {
                def summaryFile = 'allure-report/widgets/summary.json'
                def summaryContent = readFile(summaryFile)

                def json = new JsonSlurper().parseText(summaryContent)

                def passedCount = json.statistic.passed
                def totalCount = json.statistic.total
                def message = "Allure Report: ${passedCount}/${totalCount} tests passed ✅"

                def botToken = '8228531250:AAF4-CNqenOBmhO_U0qOq1pcpvMDNY0RvBU'
                def chatId = '6877916742'
                sh """
                curl -s -X POST https://api.telegram.org/bot${botToken}/sendMessage \
                     -d chat_id=${chatId} \
                     -d text="${message}"
                """
            }

            echo "Pipeline finished"
        }
    }
}
