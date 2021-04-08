FROM adoptopenjdk/openjdk11
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY target/AmarisPOC.jar AmarisPOC.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/AmarisPOC.jar"]