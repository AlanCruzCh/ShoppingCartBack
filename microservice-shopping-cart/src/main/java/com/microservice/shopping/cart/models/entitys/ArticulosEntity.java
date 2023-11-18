package com.microservice.shopping.cart.models.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/*
 * Entity ArticulosEntity
 * 
 * Contains all the information about the articles
 * 
 * @author Alan F. Cruz Chavez
 * @version 1.0
 */
@Entity
@Table(name ="articulos")
public class ArticulosEntity{

    // * *********************************************************************************************************
	// * Defines the attributes
	// * *********************************************************************************************************  

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_articulo", nullable = false )
	private Integer idArticulo;

	@NotEmpty
	@Column(length = 1000, nullable = false)
	private String description;

	@NotNull
	@Column(nullable = false)
	private Float precio;

	@NotNull
	@Column(nullable = false)
	private Integer cantidad;

	@NotNull
	@Lob
	@Column(columnDefinition = "LONGBLOB", nullable = false)
	private byte[] fotografia;

	// * *********************************************************************************************************
	// * Define the getters and setters methods
	// * *********************************************************************************************************

	public Integer getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(Integer idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public byte[] getFotografia() {
		return fotografia;
	}

	public void setFotografia(byte[] fotografia) {
		this.fotografia = fotografia;
	}

	// * *********************************************************************************************************
	// * Define the constructors
	// * *********************************************************************************************************
	
	public ArticulosEntity() {
	}

	public ArticulosEntity(@NotEmpty String description, @NotNull Float precio, @NotNull Integer cantidad,
			@NotNull byte[] fotografia) {
		this.description = description;
		this.precio = precio;
		this.cantidad = cantidad;
		this.fotografia = fotografia;
	}	

}
