# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory to /app
WORKDIR /app

# Copy the Maven project file into the container
COPY pom.xml .

# Download the dependencies
RUN mvn dependency:go-offline

# Copy the source code into the container
COPY src/ src/

# Build the JAR file
RUN mvn package

# Copy the JAR file into the container
COPY target/hidden-pearls-0.0.1-SNAPSHOT.jar .

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file when the container launches
CMD ["java", "-jar", "hidden-pearls-0.0.1-SNAPSHOT.jar"]
