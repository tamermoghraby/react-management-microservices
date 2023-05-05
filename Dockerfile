FROM openjdk:17
COPY ./build/libs/cloud-gateway-0.0.1.jar tamermg/cloud-gateway.jar
EXPOSE 9191
ENTRYPOINT [ "java", "-jar", "tamermg/cloud-gateway.jar" ]
