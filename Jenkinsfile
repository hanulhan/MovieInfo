node {
   stage ('Build') {
      git url: 'https://github.com/hanulhan/movie.git'
      withMaven {
         sh "mvn clean verify"
      }
}

