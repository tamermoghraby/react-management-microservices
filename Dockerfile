FROM openjdk:17-alpine
ARG JAR_FILE=build/libs/*.jar 
COPY ./build/libs/auth-service-0.0.1.jar tamermg/auth-service.jar
EXPOSE 9898
ENTRYPOINT ["java","-jar","tamermg/auth-service.jar"]
