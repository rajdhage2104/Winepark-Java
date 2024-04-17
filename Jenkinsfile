pipeline {
    agent any
       
    tools {
        maven 'Maven3'
    }

    stages {
        stage('Build and Coverage') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'github', url: 'https://github.com/rajdhage2104/Winepark-Java.git']]])
                sh 'mvn clean install'
            }
            post {
                success {
                    jacoco()
                    junit '**/target/surefire-reports/**/*.xml'
                    archiveArtifacts 'target/*.jar'
                }
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
