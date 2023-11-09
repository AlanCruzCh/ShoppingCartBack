package com.microservice.shopping.cart.services;

import java.util.List;

import com.microservice.shopping.cart.models.dtoRecivers.ArticuloDTO;
import com.microservice.shopping.cart.models.dtoRecivers.CarritoCompraDTO;
import com.microservice.shopping.cart.models.entitys.ArticulosEntity;

public interface ShoppingCartCN {
    
    /*
     * *****************************************************************************
     * Methods that store information
     * *****************************************************************************
     */

    public void saveArticle(ArticuloDTO dataJson);

    public void saveShoppingCart(CarritoCompraDTO dataJson);

    
    /*
	 * *****************************************************************************
	 * Methods to search for information
	 * *****************************************************************************
	 */

    public List<ArticulosEntity> findAllArticlesByDescription(String word);




    /*
	 * *****************************************************************************
	 * Methods to update information
	 * *****************************************************************************
	 */

    /*
     * *****************************************************************************
     * Methods to delete information
     * *****************************************************************************
     */

    public void deleteShoppingCart();

}
