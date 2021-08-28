pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh 'sh "mvn -Dmaven.test.skip=true clean package"'
      }
    }

  }
}