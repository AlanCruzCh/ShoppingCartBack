package com.microservice.shopping.cart.models.dtoResponses;

import org.springframework.data.domain.Page;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CarritoDto {
    
    @NotEmpty
    private Page<ArticuloCarritoDto> articulos;

    @NotNull
    private Double costoTotal;

    public Page<ArticuloCarritoDto> getArticulos() {
        return articulos;
    }

    public void setArticulos(Page<ArticuloCarritoDto> articulos) {
        this.articulos = articulos;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public CarritoDto() {
    }

    public CarritoDto(@NotEmpty Page<ArticuloCarritoDto> articulos, @NotNull Double costoTotal) {
        this.articulos = articulos;
        this.costoTotal = costoTotal;
    }

}
