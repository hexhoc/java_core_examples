#Start with a base image containing Java runtime
FROM openjdk:17
# Add Maintainer Info
LABEL maintainer="Vladislav Zhuravsky <hexhoc@gmail.com>"
VOLUME /tmp
ADD target/*.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar