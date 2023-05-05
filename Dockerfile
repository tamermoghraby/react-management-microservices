FROM openjdk:17-alpine
ARG JAR_FILE=build/libs/*.jar 
COPY ./build/libs/user-service-0.0.1.jar tamermg/user-service.jar
EXPOSE 9002
ENTRYPOINT ["java","-jar","tamermg/user-service.jar"]
