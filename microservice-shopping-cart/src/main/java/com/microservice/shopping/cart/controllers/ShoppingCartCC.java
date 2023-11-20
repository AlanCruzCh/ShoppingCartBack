package com.microservice.shopping.cart.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.shopping.cart.exceptions.Exceptions.DataNotFound;
import com.microservice.shopping.cart.exceptions.Exceptions.DatabaseAccessException;
import com.microservice.shopping.cart.exceptions.Exceptions.MostProductException;
import com.microservice.shopping.cart.models.dtoRecivers.ArticuloDTO;
import com.microservice.shopping.cart.models.dtoRecivers.CarritoCompraDTO;
import com.microservice.shopping.cart.models.dtoResponses.ResponseTypeDtoMessaje;
import com.microservice.shopping.cart.models.entitys.ArticulosEntity;
import com.microservice.shopping.cart.services.ShoppingCartCN;

@RestController
@CrossOrigin(value = "http://localhost:4200")
public class ShoppingCartCC {

    @Autowired
    private ShoppingCartCN shoppingCartCN;

    @PostMapping("/new/articule")
    private ResponseEntity<?> saveArticule(@RequestBody ArticuloDTO json){
        try {
            ArticulosEntity newArticule = shoppingCartCN.saveArticle(json);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseTypeDtoMessaje("Se ha creado con exito el el arituvlo con el id "+ newArticule.getIdArticulo()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getMessage());
        } catch (DatabaseAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    };

    @PostMapping("/new/carrito")
    private ResponseEntity<?> saveCarrito(@RequestBody CarritoCompraDTO json){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseTypeDtoMessaje(shoppingCartCN.saveShoppingCart(json))
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (MostProductException e) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getMessage());
        } catch (DatabaseAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
    @GetMapping("/lista/articulos/{word}/page/{numPage}")
    private ResponseEntity<?> findArticules(@PathVariable String word, @PathVariable Integer numPage){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(shoppingCartCN.findAllArticlesByDescription(word, numPage, 5));
        } catch (DataNotFound e) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getMessage());
        } catch (DatabaseAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/carrito/page/{numPage}")
    private ResponseEntity<?> findCarrito(@PathVariable Integer numPage){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(shoppingCartCN.findCarritoCompra(numPage, 5));
        } catch (DataNotFound e) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getMessage());
        } catch (DatabaseAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
