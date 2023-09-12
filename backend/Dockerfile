FROM eclipse-temurin:latest
RUN mkdir /opt/rentcalc-app
COPY ./target/backend-0.0.1-SNAPSHOT.jar /opt/rentcalc-app
CMD "java" "-jar" "/opt/rentcalc-app/backend-0.0.1-SNAPSHOT.jar"
# docker build -t rentcalc-app -f Dockerfile .