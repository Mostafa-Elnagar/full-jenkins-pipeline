@Library('java-sharedlib@main') _

pipeline {
    agent {
        label 'agent-02'
    }
    
    parameters {
        string(name: 'IMAGE_NAME', defaultValue: 'java-app', description: 'Docker image name')
        string(name: 'IMAGE_TAG', defaultValue: "1.${env.BUILD_NUMBER}", description: 'Docker image tag')
        string(name: 'DOCKER_REGISTRY', defaultValue: 'docker.io', description: 'Docker registry')
        string(name: 'REGISTRY_NAMESPACE', defaultValue: 'mostafaelnagar', description: 'Docker namespace')
        string(name: 'DOCKER_CREDS_ID', defaultValue: 'dockerHubCreds', description: 'My docker credientials')
        booleanParam(name: 'SKIP_TESTS', defaultValue: false, description: 'Skip running tests')
        booleanParam(name: 'PUSH_IMAGE', defaultValue: true, description: 'Push image to registry')
        choice(name: 'BUILD_TYPE', choices: ['full', 'quick'], description: 'Build type')
    }
    
    environment {
        MAVEN_HOME = '/usr/share/maven'
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'
        PATH = "${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${PATH}"
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }
        
        stage('Parallel Build') {
            parallel {
                stage('Build App 1') {
                    steps {
                        dir('java-app-1') {
                            script {
                                def mavenOps = new org.iti.ops.MavenOps(this)
                                
                                if (params.BUILD_TYPE == 'full') {
                                    mavenOps.installDependencies()
                                }
                                
                                mavenOps.buildProject()
                                
                                if (!params.SKIP_TESTS) {
                                    mavenOps.runTests()
                                }
                            }
                        }
                    }
                }
                
                stage('Build App 2') {
                    steps {
                        dir('java-app-2') {
                            script {
                                def mavenOps = new org.iti.ops.MavenOps(this)
                                
                                if (params.BUILD_TYPE == 'full') {
                                    mavenOps.installDependencies()
                                }
                                
                                mavenOps.buildProject()
                                
                                if (!params.SKIP_TESTS) {
                                    mavenOps.runTests()
                                }
                            }
                        }
                    }
                }
            }
        }
        stage('Login to Registry') {
            steps {
                script {
                    def imageOps = new org.iti.ops.ImageOps(this)
                    imageOps.loginRegistry(credentialsId: params.DOCKER_CREDS_ID)
                }
            }
        }  

        stage('Build & Push Image 1') {
            steps {
                dir('java-app-1') {
                    script {
                        def imageOps = new org.iti.ops.ImageOps(this)
                        
                        imageOps.buildAndPush(
                            imageName: "${params.IMAGE_NAME}-1",
                            imageTag: params.IMAGE_TAG,
                            dockerfile: 'Dockerfile',
                            context: '.',
                            registry: params.DOCKER_REGISTRY,
                            username: params.DOCKER_USERNAME,
                        )
                    }
                }
            }
        }
        
        stage('Build & Push Image 2') {
            steps {
                dir('java-app-2') {
                    script {
                        def imageOps = new org.iti.ops.ImageOps(this)
                        
                        imageOps.buildAndPush(
                            imageName: "${params.IMAGE_NAME}-2",
                            imageTag: params.IMAGE_TAG,
                            dockerfile: 'Dockerfile',
                            context: '.',
                            registry: params.DOCKER_REGISTRY,
                            namespace: params.REGISTRY_NAMESPACE,
                        )
                    }
                }
            }
        }

        stage('Logout Registry') {
            steps {
                script {
                    def imageOps = org.iti.ops.ImageOps(this)
                    imageOps.logoutRegistry()
                }
            }
        }
    }
    post {
        always {
            script {
                postAction(
                    cleanWorkspace: true,
                    removeImages: false,
                )
            }
        }
        
        success {
            echo 'Pipeline completed successfully!'
        }
        
        failure {
            echo 'Pipeline failed!'
        }
        
        unstable {
            echo 'Pipeline is unstable!'
        }
    }
}
