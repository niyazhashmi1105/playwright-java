FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /usr/src/app
COPY . .
RUN mvn clean install

# Final container to run tests
FROM openjdk:17
WORKDIR /usr/src/app
COPY --from=build /usr/src/app .
ENTRYPOINT ["mvn", "test"]
