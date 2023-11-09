package com.microservice.shopping.cart.models.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/*
 * Entity CarritoCompraEntity
 * 
 * Contains all the information about the shopping cart
 * 
 * @author Alan F. Cruz Chavez
 * @version 1.0
 */
@Entity
@Table(name ="carrito_compra")
public class CarritoCompraEntity {
    
    // * *********************************************************************************************************
	// * Defines the attributes
	// * *********************************************************************************************************  

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false )
	private Integer id;

	@NotNull
	@Column(nullable = false)
	private Integer cantidad;

    // * *********************************************************************************************************
	// * Defines the relations of the entity
	// * *********************************************************************************************************
    
    @NotNull
    @Column(name = "id_articulo", nullable = false )
	private Integer idArticulo;

    // * *********************************************************************************************************
	// * Define the getters and setters methods
	// * *********************************************************************************************************

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    // * *********************************************************************************************************
	// * Define the constructors
	// * *********************************************************************************************************

    public CarritoCompraEntity() {
    }

    public CarritoCompraEntity(@NotNull Integer cantidad, @NotNull Integer idArticulo) {
        this.cantidad = cantidad;
        this.idArticulo = idArticulo;
    }

}
