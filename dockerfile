FROM openjdk:17
COPY target/product-api.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
