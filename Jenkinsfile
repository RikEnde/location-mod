pipeline {
  agent any

  stages {
    stage('build jar') {
      steps {
        sh "./gradlew build"
      }
    }
  }
}