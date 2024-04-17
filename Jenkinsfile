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
                    -Dsonar.projectKey=$projectkey \
                    -Dsonar.host.url=$sonarurl \
                    -Dsonar.login=$login'
                }
            }
        }
    }
}
