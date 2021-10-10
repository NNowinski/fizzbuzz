FROM debian:11.0

RUN apt-get update -yq \
&& apt install openjdk-17-jdk -y \
&& apt-get install maven -y

ADD . /app/
WORKDIR /app
RUN mvn clean package -Pprod -DskipTests
WORKDIR /app/rest/target
RUN jar -xf fizzbuzz-app.jar

EXPOSE 8080
VOLUME /app/logs

CMD java org.springframework.boot.loader.JarLauncher