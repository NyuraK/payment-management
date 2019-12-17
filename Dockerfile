FROM openjdk:8-jre-alpine
EXPOSE 8080
ARG JAR_FILE=impl/target/payment-management.jar
ADD ${JAR_FILE} payment-management.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/payment-management.jar"]