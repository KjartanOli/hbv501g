FROM maven:3.8.1-openjdk-17-slim
VOLUME /tmp
COPY .env .env
COPY pom.xml pom.xml
COPY src src
RUN mvn clean package
EXPOSE 8080
CMD ["java","-jar","/target/hidden-pearls-0.0.1-SNAPSHOT.jar"]
