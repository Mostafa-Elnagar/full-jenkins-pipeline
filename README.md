# Full Jenkins Pipeline for Java Applications

This project demonstrates a complete Jenkins pipeline setup with a shared library for building and deploying Java applications using JDK 17.

## Project Structure

```
├── sharedlib/                          # Shared Library for Jenkins
│   ├── src/
│   │   └── org/iti/
│   │       ├── vars/                   # Global Variables (Vars)
│   │       │   ├── buildJar.groovy     # Maven JAR building
│   │       │   ├── buildImage.groovy   # Docker image building
│   │       │   ├── loginRegistry.groovy # Docker registry login
│   │       │   ├── pushImage.groovy    # Docker image pushing
│   │       │   └── postAction.groovy   # Post-build cleanup
│   │       ├── imageOps/               # Image Operations Package
│   │       │   └── ImageOperations.groovy
│   │       ├── mavenOps/               # Maven Operations Package
│   │       │   └── MavenOperations.groovy
│   │       └── SharedLibrary.groovy    # Main shared library class
│   └── vars/README.md                  # Vars documentation
├── java-app-1/                         # First Java Application
│   ├── src/
│   │   ├── main/java/org/iti/App.java
│   │   └── test/java/org/iti/AppTest.java
│   ├── pom.xml                         # Maven configuration
│   └── Dockerfile                      # Docker configuration
├── java-app-2/                         # Second Java Application
│   ├── src/
│   │   ├── main/java/org/iti/App.java
│   │   └── test/java/org/iti/AppTest.java
│   ├── pom.xml                         # Maven configuration
│   └── Dockerfile                      # Docker configuration
├── Jenkinsfile                         # Main Jenkins pipeline
└── README.md                           # This file
```

## Features

### Shared Library
- **Vars Functions**: Reusable pipeline functions for common operations
- **Image Operations Package**: Docker image building and pushing operations
- **Maven Operations Package**: Maven build and test operations
- **Modular Design**: Clean separation of concerns

### Jenkins Pipeline
- **Declarative Syntax**: Modern Jenkins pipeline syntax
- **Parallel Execution**: Builds both applications simultaneously
- **Parameters**: Configurable build parameters
- **Agent Specification**: Uses `agent-02` as specified
- **Post Actions**: Automatic cleanup and notifications

### Java Applications
- **JDK 17**: Latest LTS Java version
- **Maven**: Standard build tool
- **JUnit**: Testing framework
- **Docker**: Containerization support

## Setup Instructions

### 1. Shared Library Configuration

In Jenkins, configure the shared library:
1. Go to **Manage Jenkins** → **Configure System**
2. Under **Global Pipeline Libraries**, add:
   - **Name**: `sharedlib`
   - **Default Version**: `main` or your branch
   - **Source Code Management**: Git
   - **Project Repository**: Your repository URL

### 2. Required Jenkins Plugins

- Pipeline
- Git
- Docker Pipeline
- Maven Integration
- Credentials Binding

### 3. Required Jenkins Tools

- **Maven**: Install Maven tool named `Maven`
- **JDK**: Install JDK 17 tool named `java-17`

### 4. Credentials Setup

Create credentials for Docker registry:
- **Kind**: Username with password
- **ID**: `docker-registry`
- **Username**: Your registry username
- **Password**: Your registry password

## Pipeline Parameters

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `IMAGE_NAME` | String | `java-app` | Base name for Docker images |
| `IMAGE_TAG` | String | `latest` | Tag for Docker images |
| `REGISTRY_URL` | String | `docker.io` | Docker registry URL |
| `SKIP_TESTS` | Boolean | `false` | Skip running tests |
| `PUSH_IMAGE` | Boolean | `true` | Push images to registry |
| `BUILD_TYPE` | Choice | `full` | Build type (full/quick) |

## Usage Examples

### Basic Pipeline Run
```bash
# Run with default parameters
jenkins build

# Run with custom image name and tag
jenkins build -p IMAGE_NAME=myapp -p IMAGE_TAG=v1.0.0
```

### Custom Build Configuration
```bash
# Skip tests and use quick build
jenkins build -p SKIP_TESTS=true -p BUILD_TYPE=quick

# Use custom registry
jenkins build -p REGISTRY_URL=myregistry.com
```

## Pipeline Stages

1. **Checkout**: Source code checkout
2. **Parallel Build**: Build both Java applications simultaneously
3. **Build Images**: Create Docker images for both applications
4. **Push Images**: Push images to registry (conditional)
5. **Post Actions**: Cleanup and notifications

## Shared Library Functions

### Maven Operations
- `buildProject()`: Build the Maven project
- `runTests()`: Execute Maven tests
- `installDependencies()`: Install Maven dependencies

### Image Operations
- `buildAndPush()`: Complete image build and push workflow
- Individual functions for each step (build, login, push)

### Utility Functions
- `postAction()`: Cleanup operations
- All functions support configuration parameters

## Best Practices

1. **Modularity**: Each operation is in its own package
2. **Reusability**: Functions can be used across different projects
3. **Configuration**: All functions support parameter customization
4. **Error Handling**: Proper error handling and logging
5. **Cleanup**: Automatic workspace and image cleanup

## Troubleshooting

### Common Issues

1. **Maven not found**: Ensure Maven tool is configured in Jenkins
2. **Docker permission denied**: Check Docker daemon permissions
3. **Registry login failed**: Verify credentials configuration
4. **Build failures**: Check Java version compatibility

### Debug Mode

Enable pipeline debug logging in Jenkins:
1. Go to **Manage Jenkins** → **Configure System**
2. Set **Pipeline Logging Level** to `FINEST`

## Contributing

1. Follow the existing code structure
2. Add proper documentation for new functions
3. Include unit tests where applicable
4. Update this README for new features

## License

This project is for educational and demonstration purposes.