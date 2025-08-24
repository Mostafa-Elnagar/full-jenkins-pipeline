#!/usr/bin/env groovy

def call(Map config = [:]) {
    def imageName = config.imageName ?: 'java-app'
    def imageTag = config.imageTag ?: 'latest'
    def registryUrl = config.registryUrl ?: 'docker.io'
    def fullImageName = "${registryUrl}/${imageName}:${imageTag}"
    
    echo "Pushing Docker image: ${fullImageName}"
    
    sh "docker tag ${imageName}:${imageTag} ${fullImageName}"
    sh "docker push ${fullImageName}"
    
    echo "Docker image pushed successfully: ${fullImageName}"
}
