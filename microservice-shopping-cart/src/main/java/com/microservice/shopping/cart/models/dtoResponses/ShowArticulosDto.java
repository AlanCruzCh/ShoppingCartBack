package com.microservice.shopping.cart.models.dtoResponses;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ShowArticulosDto {

    @NotNull
    private Integer idArticle;

    @NotEmpty
    private String descripcion;

    @NotNull
    private Float precio;

    @NotEmpty
    private String fotografia;

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public Integer getIdArticle() {
        return idArticle;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public String getFotografia() {
        return fotografia;
    }

    public ShowArticulosDto() {
    }

    public ShowArticulosDto(@NotNull Integer idArticle, @NotEmpty String descripcion, @NotNull Float precio,
            @NotEmpty String fotografia) {
        this.idArticle = idArticle;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fotografia = fotografia;
    }

}
