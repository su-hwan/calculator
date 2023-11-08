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
				stage("Docker push") {
						steps {
								sh "docker push kimsuhwan/calculator"
						}
				}
				stage("Deploying to staging") {
						steps {
								sh "docker run -d --rm -p 8765:8080 --name calculator kimsuhwan/calculator"
						}
				}
				stage("Acceptance test") {
						steps {
								sleep 60
								sh "chmod +x acceptance_test.sh && ./acceptance_test.sh"
						}
				}
		}

		post {
				always {
						sh "docker stop calculator"
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
