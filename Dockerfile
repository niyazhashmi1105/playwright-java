# use maven latest version and update in the curl command url
# docker build -t playwright-java .
# docker run -d -p 8080:8080 --name playwright-java-container playwright-java
# docker cp playwright-java-container:/app/reports/index.html ./index.html

# Use the official OpenJDK 17 image as the base for building
FROM openjdk:17-slim

# Install required system packages for Playwright and browsers
RUN apt-get update && apt-get install -y --no-install-recommends \
    curl \
    gnupg \
    libnss3 \
    libgconf-2-4 \
    libxss1 \
    libx11-xcb1 \
    libxrandr2 \
    libxcomposite1 \
    libxcursor1 \
    libxi6 \
    libxtst6 \
    libasound2 \
    libatk-bridge2.0-0 \
    libgtk-3-0 \
    libdbus-glib-1-2 \
    libatspi2.0-0 \
    fonts-liberation \
    libappindicator3-1 \
    xdg-utils \
    wget \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Install Maven
RUN curl -fsSL https://downloads.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.tar.gz -o maven.tar.gz \
    && tar -xzf maven.tar.gz -C /opt/ \
    && ln -s /opt/apache-maven-3.9.9/bin/mvn /usr/bin/mvn \
    && rm maven.tar.gz

# Set the working directory
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src
COPY testng.xml .

# Tests
RUN mvn clean test
RUN ls -la /app/

# Copy the reports from docker container to current reports directory
COPY /app/reports ./reports
