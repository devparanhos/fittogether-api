FROM maven:3.9-amazoncorretto-20
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:20
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]