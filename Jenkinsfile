pipeline {
    agent any
     environment{

        ecrRegistryUrl = credentials('ECR_REGISTRY_URL')
        AWS_ACCESS_KEY_ID     = credentials('AWS_ACCESS_KEY_ID')
        AWS_SECRET_ACCESS_KEY = credentials('AWS_SECRET_ACCESS_KEY')
        AWS_REGION            = 'us-east-1'
        
     }
       
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
                    sh 'mvn clean verify sonar:sonar -Dsonar.projectKey=$projectkey -Dsonar.host.url=$sonarurl -Dsonar.login=$login'
                }
            }
        }

        stage('AWS Configuration') {
            steps {
                // Optional: Explicitly run aws configure with environment variables
                sh "aws configure set aws_access_key_id ${AWS_ACCESS_KEY_ID}"
                sh "aws configure set aws_secret_access_key ${AWS_SECRET_ACCESS_KEY}"
                sh "aws configure set default.region ${AWS_REGION}"
            }
        }

        stage('Docker build'){
            steps{
                script {
                    // Retrieve the commit SHA from the Jenkins environment
                    def shortCommitSha = sh(returnStdout: true, script: 'git rev-parse --short HEAD').trim()
        
                    // Build the image with the commit SHA as the tag
                    sh "docker build -t webapp:${shortCommitSha} ."  // Replace with your Dockerfile location
                }
            }
        }

        

        stage('Login to ECR') {
            steps{
                 sh "aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin ${ecrRegistryUrl}"
            }
        }

        stage('Push Image to ECR') {
            steps {
                script {
                    // Retrieve the commit SHA from the Jenkins environment
                    def shortCommitSha = sh(returnStdout: true, script: 'git rev-parse --short HEAD').trim()
        
                    // Tag the image with the ECR repository name
                    sh "docker tag webapp:${shortCommitSha} ${ecrRegistryUrl}:${shortCommitSha}"  // Replace with your ECR repository name
                    
                    // Push the image to ECR
                    sh "docker push ${ecrRegistryUrl}:${shortCommitSha}"
                }
            }
        }

        stage('Deploy to EC2 with Docker'){
            steps{
                sh 'aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 891377019205.dkr.ecr.us-east-1.amazonaws.com'
                sh "docker pull ${ecrRegistryUrl}:latest"
                sh 'docker run -d -p 5000:5000 891377019205.dkr.ecr.us-east-1.amazonaws.com/jenkins-ecr-repo:latest'
            }
        }

    }
}
