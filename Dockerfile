FROM java:8
LABEL maintainer = "sz.kasperkiewicz@gmail.com"
EXPOSE 8090
ADD target/GameShop-0.0.1-SNAPSHOT.jar GameShop-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","GameShop-0.0.1-SNAPSHOT.jar"]