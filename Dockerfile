FROM openjdk:11
VOLUME /tmp
ADD ./target/tarjetaapi-0.0.1-SNAPSHOT.jar tarjetaapi.jar
ENTRYPOINT ["java","-jar","/tarjetaapi.jar"]