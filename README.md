# Full Jenkins Pipeline Project

A comprehensive CI/CD pipeline project demonstrating Jenkins automation for building, testing, and deploying multiple Java applications with Docker containerization.

## 🏗️ Project Structure

```
full-jenkins-pipeline/
├── Jenkinsfile                 # Main Jenkins pipeline definition
├── java-app-1/                 # First Java application
│   ├── Dockerfile             # Container configuration for app 1
│   ├── pom.xml                # Maven configuration
│   └── src/                   # Source code and tests
├── java-app-2/                 # Second Java application
│   ├── Dockerfile             # Container configuration for app 2
│   ├── pom.xml                # Maven configuration
│   └── src/                   # Source code and tests
└── screenshots/                # Documentation images
```

## 🚀 Features

- **Multi-Application Pipeline**: Builds and deploys two Java applications simultaneously
- **Parallel Execution**: Optimized build process with parallel stages
- **Docker Integration**: Automatic containerization and registry push
- **Flexible Build Options**: Support for full and quick build types
- **Configurable Parameters**: Customizable image names, tags, and registry settings
- **Shared Library Integration**: Uses custom Jenkins shared library for operations

## 🛠️ Technology Stack

- **Java**: JDK 17
- **Maven**: Build tool and dependency management
- **Docker**: Containerization
- **Jenkins**: CI/CD automation
- **JUnit**: Testing framework

## 📋 Prerequisites

- Jenkins server with agent labeled `agent-02`
- Docker Hub credentials configured (`dockerHubCreds`)
- Maven and Java 17 installed on Jenkins agent
- Custom shared library `iti-jenkins-java-sharedlib`

## 🔧 Pipeline Parameters

| Parameter | Default | Description |
|-----------|---------|-------------|
| `IMAGE_NAME` | `java-app` | Base name for Docker images |
| `IMAGE_TAG` | `1.${BUILD_NUMBER}` | Image tag format |
| `REGISTRY_URL` | `docker.io` | Docker registry URL |
| `DOCKER_CREDS_ID` | `dockerHubCreds` | Docker credentials ID |
| `SKIP_TESTS` | `false` | Skip test execution |
| `PUSH_IMAGE` | `true` | Push images to registry |
| `BUILD_TYPE` | `full` | Build type: full/quick |

## 🔄 Pipeline Stages

1. **Checkout**: Source code retrieval
2. **Parallel Build**: Simultaneous Maven builds for both applications
3. **Build & Push Images**: Docker image creation and registry push
4. **Post Actions**: Cleanup and status reporting

## 🐳 Docker Images

Both applications are containerized using:
- **Base Image**: `openjdk:17-jre-slim`
- **Port**: 8080
- **Naming**: `{IMAGE_NAME}-1` and `{IMAGE_NAME}-2`

## 📱 Applications

### Java App 1
- Simple console application with greeting message
- Maven artifact: `java-app-1-1.0.0.jar`

### Java App 2  
- Similar structure to App 1 with different greeting
- Maven artifact: `java-app-2-1.0.0.jar`

## 🚀 Usage

1. **Setup Jenkins**: Ensure all prerequisites are configured
2. **Create Pipeline**: Use the Jenkinsfile in a new Jenkins pipeline job
3. **Configure Credentials**: Set up Docker Hub credentials
4. **Run Pipeline**: Execute with desired parameters

## 📸 Screenshots

See the `screenshots/` directory for visual documentation of the setup process.

## 🔍 Customization

The pipeline uses a shared library (`org.iti.ops`) for:
- Maven operations (`MavenOps`)
- Docker operations (`ImageOps`)
- Post-pipeline actions

Modify the shared library to extend functionality or customize build processes.
