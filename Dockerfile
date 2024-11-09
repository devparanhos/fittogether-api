FROM maven:3.9-amazoncorretto-20 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:20
WORKDIR /app
COPY --from=build ./app/target/*.jar ./fittogether-api.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","fittogether-api.jar"]