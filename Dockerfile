FROM debian:11.0

RUN apt-get update -yq \
&& apt install openjdk-17-jdk -y \
&& apt-get install maven -y

RUN mkdir /app

ADD . /sources/
WORKDIR /sources
RUN mvn clean package -Pprod -DskipTests
RUN mv /sources/rest/target/fizzbuzz-app.jar /app/fizzbuzz-app.jar

WORKDIR /app
RUN jar -xf fizzbuzz-app.jar

EXPOSE 8080
VOLUME /app/logs

CMD java org.springframework.boot.loader.JarLauncher