properties([
    parameters([
       string(description: 'Input node ip', name: 'SSHNODE', trim : true)
       ])
    ])

node {
    withCredentials([sshUserPrivateKey(credentialsId: 'jenkins1', keyFileVariable: 'SSHKEY', usernameVariable: 'SSHUSERNAME')]) {
        stage("initialize") { 
            sh "ssh  -o StrictHostKeyChecking=no -i $SSHKEY $SSHUSERNAME@${params.SSHNODE} yum install epel-release -y"
        }
        stage("install java") {
            sh "ssh  -o StrictHostKeyChecking=no -i $SSHKEY $SSHUSERNAME@${params.SSHNODE} yum install  java -y"
        }     
        stage("install git") {
            sh "ssh  -o StrictHostKeyChecking=no -i $SSHKEY $SSHUSERNAME@${params.SSHNODE} yum install git -y"
        }
        stage("install ansible") {
            sh "ssh  -o StrictHostKeyChecking=no -i $SSHKEY $SSHUSERNAME@${params.SSHNODE} yum install ansible -y"
        }
    }
}      