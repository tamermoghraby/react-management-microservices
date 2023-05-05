FROM openjdk:17-alpine
WORKDIR /app 
COPY ./build/libs/service-registry-0.0.1-SNAPSHOT.jar tamermg/service-registry.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","tamermg/service-registry.jar"]
