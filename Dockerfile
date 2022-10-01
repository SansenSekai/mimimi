FROM openjdk:11
LABEL maintainer="projects.test.com"
ADD target/mimimi-1.0.0.jar mimimi.jar
EXPOSE 9900
ENTRYPOINT ["java", "-jar", "mimimi.jar"]