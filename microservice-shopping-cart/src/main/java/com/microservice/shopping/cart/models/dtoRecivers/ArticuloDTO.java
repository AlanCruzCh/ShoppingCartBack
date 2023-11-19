package com.microservice.shopping.cart.models.dtoRecivers;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/*
 * Data Transfer Object ArticuloDTO
 * 
 * Contains all the information about the articles dto
 * 
 * @author Alan F. Cruz Chavez
 * @version 1.0
 */
public class ArticuloDTO {

    // * *********************************************************************************************************
	// * Defines the attributes of DTO
	// * ********************************************************************************************************* 

    @NotEmpty
	private String description;

	@NotNull
	private Float precio;

	@NotNull
	private Integer cantidad;

	@NotNull
	private String fotografia;

    public String getDescription() {
        return description;
    }

    public Float getPrecio() {
        return precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public String getFotografia() {
        return fotografia;
    }
    
}
