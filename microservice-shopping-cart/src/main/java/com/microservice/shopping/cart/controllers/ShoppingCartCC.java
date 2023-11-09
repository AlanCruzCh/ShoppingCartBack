package com.microservice.shopping.cart.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.shopping.cart.models.dtoRecivers.ArticuloDTO;
import com.microservice.shopping.cart.models.entitys.ArticulosEntity;
import com.microservice.shopping.cart.services.ShoppingCartCN;

@RestController
public class ShoppingCartCC {

    @Autowired
    private ShoppingCartCN shoppingCartCN;

    @PostMapping("/articule")
    private ResponseEntity<?> saveArticule(@RequestBody ArticuloDTO json){
        try {
            ArticulosEntity newArticule = shoppingCartCN.saveArticle(json);
            return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado con exito el el arituvlo con el id "+ newArticule.getIdArticulo());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    };

}
