package com.microservice.shopping.cart.services;

import org.springframework.data.domain.Page;

import com.microservice.shopping.cart.models.dtoRecivers.ArticuloDTO;
import com.microservice.shopping.cart.models.dtoRecivers.CarritoArticuloUpdateDto;
import com.microservice.shopping.cart.models.dtoRecivers.CarritoCompraDTO;
import com.microservice.shopping.cart.models.dtoResponses.CarritoDto;
import com.microservice.shopping.cart.models.dtoResponses.ShowArticulosDto;
import com.microservice.shopping.cart.models.entitys.ArticulosEntity;

public interface ShoppingCartCN {
    
    /*
     * *****************************************************************************
     * Methods that store information
     * *****************************************************************************
     */

    public ArticulosEntity saveArticle(ArticuloDTO dataJson);

    public String saveShoppingCart(CarritoCompraDTO dataJson);

    
    /*
	 * *****************************************************************************
	 * Methods to search for information
	 * *****************************************************************************
	 */

    public Page<ShowArticulosDto> findAllArticlesByDescription(String word, Integer numPagina, Integer tamPagina);

    public CarritoDto findCarritoCompra(Integer numPagina, Integer tamPagina);


    /*
	 * *****************************************************************************
	 * Methods to update information
	 * *****************************************************************************
	 */

    public String updateArticuloCarrito(CarritoArticuloUpdateDto dataJson);

    /*
     * *****************************************************************************
     * Methods to delete information
     * *****************************************************************************
     */

    public String deleteShoppingCart();

    public String deleteArticuloCarrito(Integer idArticulo);

}
