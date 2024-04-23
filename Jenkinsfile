pipeline {
    agent any
     environment{
        // Define environment variables for AWS and ECR credentials
        ecrRegistryUrl        = credentials('ECR_REGISTRY_URL')
        AWS_ACCESS_KEY_ID     = credentials('AWS_ACCESS_KEY_ID')
        AWS_SECRET_ACCESS_KEY = credentials('AWS_SECRET_ACCESS_KEY')
        AWS_REGION            = 'us-east-1'
        INSTANCE_PUBLIC_IP = credentials('INSTANCE_PUBLIC_IP')
        SHORT_COMMIT_SHA = sh(returnStdout: true, script: 'git rev-parse --short HEAD').trim()
     }
       
    tools {
        // Define the tools required for the pipeline
        maven 'Maven3'
    }

    stages {
        stage('Build and Coverage') {
            steps {
                // Checkout the source code from Git an
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'github', url: 'https://github.com/rajdhage2104/Winepark-Java.git']]])
                
                // Build the Maven project
                sh 'mvn clean install' 
            }

            post {
                success {
                    // Publish JaCoCo coverage report
                    jacoco()

                    // Publish JUnit test results
                    junit '**/target/surefire-reports/**/*.xml'

                    // Upload code-coverage report as an artifact
                    archiveArtifacts 'target/*.jar'  
                }
            }
        }

        stage('Sonarqube Analysis') {
            steps{
                // Run SonarQube analysis
                withSonarQubeEnv('sonarqube') {
                    sh 'mvn clean verify sonar:sonar -Dsonar.projectKey=$projectkey -Dsonar.host.url=$sonarurl -Dsonar.login=$login'
                }
            }
        }

        stage('AWS Configuration') {
            steps {
                // Configure AWS credentials
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
                    sh "docker build -t webapp:${shortCommitSha} ."  
                }
            }
        }

        

        stage('Login to ECR') {
            steps{
                 // Log in to ECR
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

        stage('AWS Configuration') {
            steps {
                // Configure AWS credentials
                sh "aws configure set aws_access_key_id ${AWS_ACCESS_KEY_ID}"
                sh "aws configure set aws_secret_access_key ${AWS_SECRET_ACCESS_KEY}"
                sh "aws configure set default.region ${AWS_REGION}"
                sh "aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin ${ecrRegistryUrl}"
            }
        }



        stage('Deploy to EC2 with Docker') {
            steps{
               script {
            // SSH into EC2 instance
            sshCommand = "ssh -i ~/.ssh/id_rsa ubuntu@${INSTANCE_PUBLIC_IP} '"
            sshCommand += "sudo docker pull ${ecrRegistryUrl}:${shortCommitSha};"
            sshCommand += "sudo docker run -d -p 5000:5000 ${ecrRegistryUrl}:${shortCommitSha}'"
            sh sshCommand
                }
            }
        }   

    }

}
