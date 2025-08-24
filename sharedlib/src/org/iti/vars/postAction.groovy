#!/usr/bin/env groovy

def call(Map config = [:]) {
    def cleanWorkspace = config.cleanWorkspace ?: true
    def removeImages = config.removeImages ?: false
    def imageName = config.imageName ?: 'java-app'
    def imageTag = config.imageTag ?: 'latest'
    
    echo "Performing post-build cleanup..."
    
    if (cleanWorkspace) {
        cleanWs()
        echo "Workspace cleaned"
    }
    
    if (removeImages) {
        sh "docker rmi ${imageName}:${imageTag} || true"
        echo "Docker images removed"
    }
    
    echo "Post-build cleanup completed"
}
