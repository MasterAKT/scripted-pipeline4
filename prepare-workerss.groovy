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
        stage("Install Terraform") {
            sh "ssh -o StrictHostKeyChecking=no -i $SSHKEY $SSHUSERNAME@${ params.SSHNODE } 'yum install -y wget unzip && wget https://releases.hashicorp.com/terraform/0.13.1/terraform_0.13.1_linux_amd64.zip && unzip terraform_0.13.1_linux_amd64.zip && mv terraform /usr/bin/'"
        }
    }
}      