# docker build -t playwright-java .
# docker run --name playwright-java-container playwright-java
# docker cp playwright-java-container:/app/reports/index.html reports/index.html
# docker stop your-container-name
# docker rm your-container-name
# docker image prune

FROM mcr.microsoft.com/playwright/java:v1.47.0-noble

# Set the working directory
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src
COPY testng.xml .

# Run the Tests
ENTRYPOINT ["mvn", "clean", "test"]

