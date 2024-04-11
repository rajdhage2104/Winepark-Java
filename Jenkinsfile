// pipeline{
//     agent any
//     tools {
//   maven 'Maven3'
//   }
//     stages{
//        stage ('pull'){
//         steps {
//             git branch: 'main', credentialsId: '5527fdce-9bef-46f5-afa5-c137c3da13af', url: 'https://github.com/rajdhage2104/Winepark-Java.git'
//             echo 'pull successfully'
//         }
//      }
//        stage ('build'){
//         steps{
//          def mvnHome = tool name: 'Apache Maven 3.6.0', type: 'maven'
//             sh 'mvn clean package'
//             echo 'build successfull'
//         }
//        }
//        stage ('test'){
//         steps{
//             echo 'test complete'
//         }
//        }
//     }
// }


pipeline {
  agent any
  tools {
    maven 'Maven3'
  }
  stages {
      stage ('pull'){
            steps {
                git branch: 'main', credentialsId: '5527fdce-9bef-46f5-afa5-c137c3da13af', url: 'https://github.com/rajdhage2104/Winepark-Java.git'
                echo 'pull successfully'
            }
          }
        stage('Build') {
          steps {
            def mvnHome = tool name: 'Maven3', type: 'maven'
            sh 'mvn -B -DskipTests clean package'
          }
        }
      }
    }
