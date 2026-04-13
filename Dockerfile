FROM openjdk:22-jdk
WORKDIR /app
COPY target/hibernate-first-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]