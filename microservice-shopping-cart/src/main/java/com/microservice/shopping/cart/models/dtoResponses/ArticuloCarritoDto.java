package com.microservice.shopping.cart.models.dtoResponses;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ArticuloCarritoDto {
    
    @NotNull
    private Integer idArticulo;

    @NotNull
    private Integer idCarrito;

    @NotEmpty
    private String fotografia;

    @NotEmpty
    private String description;

    @NotEmpty
    private Double cantidad;

    @NotNull
    private Double precio;

    @NotNull
    private Double costo;

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Integer getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Integer idCarrito) {
        this.idCarrito = idCarrito;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArticuloCarritoDto() {
    }

    public ArticuloCarritoDto(@NotNull Integer idArticulo, @NotNull Integer idCarrito, @NotEmpty String fotografia,
            @NotEmpty String description, @NotEmpty Double cantidad, @NotNull Double precio, @NotNull Double costo) {
        this.idArticulo = idArticulo;
        this.idCarrito = idCarrito;
        this.fotografia = fotografia;
        this.description = description;
        this.cantidad = cantidad;
        this.precio = precio;
        this.costo = costo;
    }

}
