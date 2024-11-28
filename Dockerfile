#FROM maven:3.9-amazoncorretto-20 AS build
#WORKDIR /app
#COPY . .
#RUN mvn clean package -DskipTests
#
#FROM openjdk:20
#WORKDIR /app
#COPY --from=build ./app/target/*.jar ./fittogether-api.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","fittogether-api.jar"]

FROM maven:3.9-amazoncorretto-20 AS build
WORKDIR /app
COPY . .
COPY app.properties /app/src/main/resources/app.properties
RUN mvn clean package -DskipTests

FROM openjdk:20
WORKDIR /app
COPY --from=build ./app/target/*.jar ./fittogether-api.jar
COPY --from=build /app/src/main/resources/app.properties ./app.properties
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","fittogether-api.jar"]