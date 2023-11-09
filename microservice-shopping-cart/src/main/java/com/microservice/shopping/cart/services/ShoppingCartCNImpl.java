package com.microservice.shopping.cart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.microservice.shopping.cart.exceptions.Exceptions.DatabaseAccessException;
import com.microservice.shopping.cart.models.dtoRecivers.ArticuloDTO;
import com.microservice.shopping.cart.models.dtoRecivers.CarritoCompraDTO;
import com.microservice.shopping.cart.models.entitys.ArticulosEntity;
import com.microservice.shopping.cart.models.entitys.CarritoCompraEntity;
import com.microservice.shopping.cart.models.repositorys.ArticulosDAO;
import com.microservice.shopping.cart.models.repositorys.CarritoCompraDAO;


@Service
public class ShoppingCartCNImpl implements ShoppingCartCN{

    @Autowired
    private ArticulosDAO articuloDAO;

    @Autowired
    private CarritoCompraDAO carritoCompraDAO;

    @Override
    @Transactional
    public ArticulosEntity saveArticle(ArticuloDTO dataJson) {
        try {
            return articuloDAO.save( new ArticulosEntity(
                dataJson.getDescription(), 
                dataJson.getPrecio(), 
                dataJson.getCantidad(), 
                dataJson.getFotografia()
                ) 
            );
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("No se ha proporcionado la infromacion necesaria para registra un articulo");
        } catch(Exception e) {
            throw new DatabaseAccessException("Se ha producido un error al conectarse a la base de datos");
        }
    }

    @Override
    @Transactional
    public CarritoCompraEntity saveShoppingCart(CarritoCompraDTO dataJson) {
        try {
            return carritoCompraDAO.save( new CarritoCompraEntity(
                dataJson.getCantidad(),
                dataJson.getIdArticulo()
                ) 
            );
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("No se ha proporcionado la infromacion necesaria para registrar un articulo en el carrito de compra");
        } catch(Exception e) {
            throw new DatabaseAccessException("Se ha producido un error al conectarse a la base de datos");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArticulosEntity> findAllArticlesByDescription(String word) {
        try {
            return articuloDAO.findArticulesByDescription(word);
        } catch(Exception e) {
            throw new DatabaseAccessException("Se ha producido un error al conectarse a la base de datos");
        }
    }

    @Override
    @Transactional
    public void deleteShoppingCart() {
        try {
            carritoCompraDAO.deleteAll();
        } catch(Exception e) {
            throw new DatabaseAccessException("Se ha producido un error al conectarse a la base de datos");
        }
    }
    
}
