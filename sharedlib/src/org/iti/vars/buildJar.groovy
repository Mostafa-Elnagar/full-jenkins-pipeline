#!/usr/bin/env groovy

def call(Map config = [:]) {
    def mavenHome = config.mavenHome ?: 'Maven'
    def goals = config.goals ?: 'clean package'
    def pomFile = config.pomFile ?: 'pom.xml'
    
    echo "Building JAR with Maven..."
    
    withMaven(maven: mavenHome) {
        sh "mvn -f ${pomFile} ${goals}"
    }
    
    echo "JAR build completed successfully"
}
