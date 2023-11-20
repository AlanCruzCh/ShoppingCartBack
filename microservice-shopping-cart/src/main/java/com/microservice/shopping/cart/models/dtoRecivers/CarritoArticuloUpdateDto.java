package com.microservice.shopping.cart.models.dtoRecivers;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CarritoArticuloUpdateDto {

    @NotNull
	private Integer cantidad;
    
    @NotNull
    private Integer idArticulo;

    @NotNull
    private Integer idCarrito;

    @NotEmpty
    private String operacion;

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

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

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public CarritoArticuloUpdateDto() {
    }

    public CarritoArticuloUpdateDto(@NotNull Integer cantidad, @NotNull Integer idArticulo, @NotNull Integer idCarrito,
            @NotEmpty String operacion) {
        this.cantidad = cantidad;
        this.idArticulo = idArticulo;
        this.idCarrito = idCarrito;
        this.operacion = operacion;
    }

}
