FROM alpine/java:21-jdk

COPY target/tech-challenge-0.0.1-SNAPSHOT.jar tech-challenge.jar

ENTRYPOINT ["java","-jar","/tech-challenge.jar"]



