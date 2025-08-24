#!/usr/bin/env groovy

def call(Map config = [:]) {
    def imageName = config.imageName ?: 'java-app'
    def imageTag = config.imageTag ?: 'latest'
    def dockerfile = config.dockerfile ?: 'Dockerfile'
    def context = config.context ?: '.'
    
    echo "Building Docker image: ${imageName}:${imageTag}"
    
    sh "docker build -f ${dockerfile} -t ${imageName}:${imageTag} ${context}"
    
    echo "Docker image built successfully: ${imageName}:${imageTag}"
}
