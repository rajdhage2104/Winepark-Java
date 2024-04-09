FROM openjdk:17
COPY target/WineParkApplication.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
