FROM adoptopenjdk:11-jre-hotspot
VOLUME ["/tmp", "/config", "/data"]
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ajarindong-api.jar
ENTRYPOINT ["java","-jar","/ajarindong-api.jar"]