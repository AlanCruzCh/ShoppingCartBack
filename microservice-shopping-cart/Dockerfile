FROM openjdk:17.0.2

WORKDIR /app/shopping-cart-back

COPY ./target/microservice-shopping-cart-0.0.1-SNAPSHOT.jar /app/shopping-cart-back/

EXPOSE 0

ENTRYPOINT [ "java", "-jar", "microservice-shopping-cart-0.0.1-SNAPSHOT.jar" ]