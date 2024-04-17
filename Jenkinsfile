pipeline {
    agent any
       
    tools {
        maven 'Maven3'
    }

    stages {
        stage('Build') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'github', url: 'https://github.com/rajdhage2104/Winepark-Java.git']]])
                sh 'mvn clean install'
            }
        }

        stage('Sonarqube Analysis') {
            steps{
                withSonarQubeEnv('sonarqube') {
                    sh 'mvn clean verify sonar:sonar \
                    -Dsonar.projectKey=Java-app \
                    -Dsonar.host.url=http://54.209.83.194:9000 \
                    -Dsonar.login=sqp_f1a4dc4b0fd8c58a5b46f89bfbe5630d6373bb9c'
                }
            }
        }
    }
}
