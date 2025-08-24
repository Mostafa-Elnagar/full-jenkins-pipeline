#!/usr/bin/env groovy

def call(Map config = [:]) {
    def registryUrl = config.registryUrl ?: 'docker.io'
    def credentialsId = config.credentialsId ?: 'docker-registry'
    
    withCredentials([usernamePassword(credentialsId: 'docker-registry', 
                                   usernameVariable: 'REGISTRY_USER', 
                                   passwordVariable: 'REGISTRY_PASS')]) {
        sh "echo ${REGISTRY_PASS} | docker login ${registryUrl} -u ${REGISTRY_USER} --password-stdin"
    }
    
    echo "Successfully logged into registry: ${registryUrl}"
}
