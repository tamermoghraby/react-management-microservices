FROM openjdk:17-alpine
COPY ./build/libs/department-service-0.0.1.jar tamermg/department-service.jar
EXPOSE 9001
ENTRYPOINT ["java","-jar","tamermg/department-service.jar"]
