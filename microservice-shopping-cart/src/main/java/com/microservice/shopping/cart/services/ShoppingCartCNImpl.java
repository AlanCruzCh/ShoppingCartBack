package com.microservice.shopping.cart.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.microservice.shopping.cart.exceptions.Exceptions.DataNotFound;
import com.microservice.shopping.cart.exceptions.Exceptions.DatabaseAccessException;
import com.microservice.shopping.cart.models.dtoRecivers.ArticuloDTO;
import com.microservice.shopping.cart.models.dtoResponses.ShowArticulosDto;
import com.microservice.shopping.cart.models.entitys.ArticulosEntity;
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
            throw new IllegalArgumentException("No se ha proporcionado la infromacion necesaria para registrar un articulo");
        } catch(Exception e) {
            throw new DatabaseAccessException("Se ha producido un error al conectarse a la base de datos");
        }
    }

    /*@Override
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
    }*/

    @Override
    @Transactional(readOnly = true)
    public Page<ShowArticulosDto> findAllArticlesByDescription(String word, Integer numPagina, Integer tamPagina) {
        try {
            List<ArticulosEntity> articulos = new ArrayList<>();
            Pageable pageable = PageRequest.of(numPagina, tamPagina);
            if (word.equals("''")) {
                articulos = (List<ArticulosEntity>) articuloDAO.findAll();
            } else {
                articulos = articuloDAO.findArticulesByDescription(word);
            }
            if (articulos.isEmpty()) {
                throw new DataNotFound("No se han encontrado articulos con la descripcion: " + word);
            }
            int startIndex = (int) pageable.getOffset();
            int endIndex = Math.min((startIndex + pageable.getPageSize()), articulos.size());
            List<ArticulosEntity> subLista = articulos.subList(startIndex, endIndex);
            List<ShowArticulosDto> showArticules = new ArrayList<>();
            for (ArticulosEntity articulosEntity : subLista) {
                showArticules.add(new ShowArticulosDto(
                    articulosEntity.getIdArticulo(),
                    articulosEntity.getDescription(), 
                    articulosEntity.getPrecio(), 
                    articulosEntity.getFotografia())
                );
            }
            Page<ShowArticulosDto> page = new PageImpl<>(showArticules, pageable, articulos.size());
            return page;
        } catch (DataAccessException e) {
            throw new DatabaseAccessException("Error al conectarse a la base de datos", e);
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
