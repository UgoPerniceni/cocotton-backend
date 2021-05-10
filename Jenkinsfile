pipeline {
    agent any

    stages {

        stage ('build') {
            steps {
                withMaven(maven: 'maven_3_5_0') {
                    sh 'mvn clean package'
                }
            }
        }

        stage ('deploy') {
            steps {
                withCredentials([[$class: 'UsernamePasswordMultiBinding',
                    credentialsId   : 'IBM_LOGIN',
                    usernameVariable: 'USERNAME',
                    passwordVariable: 'PASSWORD']]) {
                        sh '/usr/local/bin/ibmcloud cf login -a https://cloud.ibm.com -u $USERNAME -p $PASSWORD'
                        sh '/usr/local/bin/ibmcloud cf push'
            }
        }
    }
}