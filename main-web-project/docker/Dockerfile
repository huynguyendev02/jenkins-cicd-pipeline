FROM openjdk:17-alpine

WORKDIR /app
RUN touch /app/app.log
COPY ./target/*.jar /app/app.jar

CMD java -jar /app/app.jar >> app.log