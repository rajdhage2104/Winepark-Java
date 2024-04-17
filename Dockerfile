FROM openjdk:11
COPY target/WineParkApplication.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
