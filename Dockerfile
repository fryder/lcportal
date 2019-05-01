FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY target/lc-summary-report.jar app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar