pipeline {
    agent any
    stages {
        stage("Compile") {
            steps {
                sh "./gradlew compileJava"
            }
        }
        stage("Unit test") {
            steps {
                sh "./gradlew test"
            }
        }
        stage("Code coverage") {
            steps {
                sh "./gradlew jacocoTestReport"
                publishHTML (target: [
                    reportDir: "build/reports/jacoco/test/html",
                    reportFiles: "index.html",
                    reportName: "Jacoco Report"
                ])
                sh "./gradlew jacocoTestCoverageVerification"
            }
        }
        stage("Static code analysis") {
            steps {
                sh "./gradlew checkstyleMain"
                publishHTML (target: [
                    reportDir: "build/reports/checkstyle",
                    reportFiles: "main.html",
                    reportName: "checkstyle Report"
                ])
            }
        }
        stage("Package") {
            steps {
                sh "./gradlew build"
            }
        }
        stage("Docker build") {
            steps {
                sh "docker build -t kimsuhwan/calculator"
            }
         }
    }


    /*
    post {
        always {
            mail to: 'xcox@nate.com',
            subject: 'Completed pipeline: ${currentBuild.fullDisplayNam}',
            body: "Your build completed, please check: ${env.BUILD_URL}"
        }
    }
    */
}