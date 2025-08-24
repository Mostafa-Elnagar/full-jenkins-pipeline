# Shared Library Vars

This directory contains reusable functions for Jenkins pipelines.

## Available Functions

### buildJar
Builds a JAR file using Maven.

**Parameters:**
- `mavenHome`: Maven installation name (default: 'Maven')
- `goals`: Maven goals (default: 'clean package')
- `pomFile`: Path to pom.xml (default: 'pom.xml')

**Usage:**
```groovy
buildJar(
    mavenHome: 'Maven',
    goals: 'clean package',
    pomFile: 'pom.xml'
)
```

### buildImage
Builds a Docker image.

**Parameters:**
- `imageName`: Name of the image (default: 'java-app')
- `imageTag`: Tag of the image (default: 'latest')
- `dockerfile`: Path to Dockerfile (default: 'Dockerfile')
- `context`: Build context (default: '.')

**Usage:**
```groovy
buildImage(
    imageName: 'my-app',
    imageTag: 'v1.0.0',
    dockerfile: 'Dockerfile',
    context: '.'
)
```

### loginRegistry
Logs into a Docker registry.

**Parameters:**
- `registryUrl`: Registry URL (default: 'docker.io')
- `username`: Registry username
- `password`: Registry password

**Usage:**
```groovy
loginRegistry(
    registryUrl: 'docker.io',
    username: 'myuser',
    password: 'mypass'
)
```

### pushImage
Pushes a Docker image to registry.

**Parameters:**
- `imageName`: Name of the image (default: 'java-app')
- `imageTag`: Tag of the image (default: 'latest')
- `registryUrl`: Registry URL (default: 'docker.io')

**Usage:**
```groovy
pushImage(
    imageName: 'my-app',
    imageTag: 'v1.0.0',
    registryUrl: 'docker.io'
)
```

### postAction
Performs post-build cleanup actions.

**Parameters:**
- `cleanWorkspace`: Clean workspace (default: true)
- `removeImages`: Remove Docker images (default: false)
- `imageName`: Image name for removal (default: 'java-app')
- `imageTag`: Image tag for removal (default: 'latest')

**Usage:**
```groovy
postAction(
    cleanWorkspace: true,
    removeImages: true,
    imageName: 'my-app',
    imageTag: 'v1.0.0'
)
```
