# ShoppingCartBack

This project was genetaed with [Spring Initializr](https://start.spring.io/) version 3.1.5

## Description 

Microservices in charge of processing the back for the shopping cart

## Deployment in Production

### Comando para crear la imagen de la aplicacion

docker build -t mcs-shopping-cart-image .


### Comando para levantar la aplicacion web en el contenedor de docker

docker run -d --name MICROSERVICE-SHOPPING-CART -p 8500:8500 -e URL_DB=database-shopping-cart -e USER_DB=Alan -e PASSWORD_DB=contrasea-del-usuario-Alan --network=SHOPPING-CART-NETWORK mcs-shopping-cart-image