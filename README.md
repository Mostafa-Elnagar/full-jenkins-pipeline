# Jenkins Pipeline Project

CI/CD pipeline that builds and deploys two Java applications using Jenkins and Docker.

## Quick Start

1. **Clone**
   ```bash
   git clone https://github.com/Mostafa-Elnagar/full-jenkins-pipeline.git
   ```

2. **Prerequisites**
   - Jenkins with agent labeled `agent-02`
   - Docker Hub credentials (`dockerHubCreds`)
   - Maven + Java 17 on agent
   - Shared library: `iti-jenkins-java-sharedlib`

3. **Use**
   - Copy `Jenkinsfile` to new Jenkins pipeline job
   - Configure Docker credentials
   - Run pipeline

## Structure

```
├── Jenkinsfile          # Pipeline definition
├── java-app-1/          # First Java app
├── java-app-2/          # Second Java app
└── screenshots/         # Setup docs
```

## Features

- **Parallel builds** for both applications
- **Docker integration** with registry push
- **Parameterized builds** (image names, tags, options)
- **Shared library** for operations

## Build Parameters

| Parameter | Default | Description |
|-----------|---------|-------------|
| IMAGE_NAME | java-app | Docker image base name |
| IMAGE_TAG | 1.${BUILD_NUMBER} | Image tag format |
| BUILD_TYPE | full | Build type: full/quick |
| SKIP_TESTS | false | Skip test execution |

## Docker Images

- **Base**: `eclipse-temurin:17-jre-jammy`
- **Port**: 8080
- **Names**: `{IMAGE_NAME}-1`, `{IMAGE_NAME}-2`

## Related

- **Shared Library**: [iti-jenkins-java-sharedlib](https://github.com/Mostafa-Elnagar/iti-jenkins-java-sharedlib)
