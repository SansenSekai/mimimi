FROM openjdk:11
LABEL maintainer="projects.test.com"
ADD target/mimimi-0.0.1.jar mimimi.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "mimimi.jar"]