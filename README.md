[![Playwright Java Tests](https://github.com/niyazhashmi1105/playwright-java/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/niyazhashmi1105/playwright-java/actions/workflows/ci.yml)

Playwright Java Project
This project is an automation testing framework built using Playwright in Java. It includes Maven for dependency management and TestNG for running tests. The structure supports Docker and CI/CD pipelines for continuous integration and delivery.

Project Directory Structure
playwright-java/
│
├── .github/                     # GitHub configurations (e.g., workflows)
├── .idea/                       # IDE (IntelliJ IDEA) project settings
├── .allure/                     # Allure report files
├── playwright-java/             # Core Playwright Java project files
├── reports/                     # Test report files
│   └── index.html               # HTML report for test results
├── screenshots/                 # Screenshots captured during tests
├── src/                         # Source code for the tests
├── target/                      # Compiled files and test output
├── .gitignore                   # Files and directories ignored by Git
├── docker-compose.yml           # Docker Compose configuration for the project
├── Dockerfile                   # Docker image definition
├── Jenkinsfile                  # Jenkins pipeline configuration
├── pom.xml                      # Maven POM file for dependencies and build setup
├── test-results-summary.txt     # Summary of test results
└── testng.xml                   # TestNG configuration file
Description
This Playwright-based project uses Java for writing and executing automated tests. The structure follows standard industry practices to ensure the project is scalable, maintainable, and integrated with CI/CD pipelines.

Reports and Logging
The reports/ folder contains the test result reports, such as index.html, which allows you to view the test execution results in a web browser.
Source Code and Screenshots
The src/ folder holds the source code for the automated test cases.
The screenshots/ folder stores screenshots captured during the test execution, useful for debugging or visual validation of test results.

Configuration for CI/CD
Files like docker-compose.yml, Dockerfile, and Jenkinsfile ensure that the project is set up for continuous integration and continuous delivery. This allows tests to be run in a containerized environment.

Test Execution
testng.xml contains the configuration for TestNG, defining which tests will be executed.
test-results-summary.txt provides a summary of the test results, which is helpful for a quick overview of the testing outcomes.

Technologies Used
Java: Programming language used to write Playwright tests.
Playwright: End-to-end testing framework.
Maven: Build automation tool for managing dependencies.
TestNG: Testing framework used to run and manage tests.
Docker: Containerization for isolated test environments.
Jenkins: Continuous integration server used for automating test runs.

How to Run
Clone the repository:
git clone <repository-url>
cd playwright-java

Install Maven dependencies:
mvn clean install
Run tests using TestNG:
mvn test
View Test Reports: Open reports/index.html in a web browser to view the detailed test results.

Running Tests in Docker
To run the tests in a Docker container:
Build the Docker image:
docker build -t playwright-java .

Run the container using Docker Compose:
docker-compose up

CI/CD with Jenkins
The Jenkinsfile defines the CI/CD pipeline. If using Jenkins, configure your project to run tests automatically whenever changes are pushed to the repository.

Contributions
Feel free to contribute to this project by opening a pull request or filing an issue.
