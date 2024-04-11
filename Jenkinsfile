pipeline{
    agent any
    
    stages{
       stage ('pull'){
        steps {
            git branch: 'main', credentialsId: '5527fdce-9bef-46f5-afa5-c137c3da13af', url: 'https://github.com/rajdhage2104/Winepark-Java.git'
            echo 'pull successfully'
        }
     }
       stage ('build'){
        steps{
            sh '/opt/maven/bin/mvn clean package'
            echo 'build successfull'
        }
       }
       stage ('test'){
        steps{
            echo 'test complete'
        }
       }
    }
}
