node{
    properties([
        parameters([
            string(defaultValue: '', description: 'Provide Remote Node IP', name: 'node', trim: true)
        ])
    ])

node{
    stage("pull Repo"){
        sh "rm -rf ansible-melodi && git clone https://github.com/ikambarov/ansible-melodi.git"
    }


    withCredentials([sshUserPrivateKey(credentialsId: 'jenkins1', keyFileVariable: 'SSH_KEY', passphraseVariable: '', usernameVariable: 'SSH_USERNAME')]) {
        stage("Ping"){
                sh """
                    export ANSIBLE_HOST_KEY_CHECKING=False
                    ansible-playbook -i "${params.node}," --private-key $SSH_KEY  ansible-melodi/main.yml -b -u $SSH_USERNAME 
                """
            } 
        }  
    }
}