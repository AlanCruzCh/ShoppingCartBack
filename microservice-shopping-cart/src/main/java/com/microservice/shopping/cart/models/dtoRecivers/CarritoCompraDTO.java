package com.microservice.shopping.cart.models.dtoRecivers;

import jakarta.validation.constraints.NotNull;

/*
 * Data Transfer Object CarritoCompraDTO
 * 
 * Contains all the information about the articles dto
 * 
 * @author Alan F. Cruz Chavez
 * @version 1.0
 */
public class CarritoCompraDTO {
    
    // * *********************************************************************************************************
	// * Defines the attributes of DTO
	// * ********************************************************************************************************* 

    @NotNull
	private Integer cantidad;
    
    @NotNull
    private Integer idArticulo;

    public Integer getCantidad() {
        return cantidad;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }
    
}   
