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
import com.microservice.shopping.cart.exceptions.Exceptions.MostProductException;
import com.microservice.shopping.cart.models.dtoRecivers.ArticuloDTO;
import com.microservice.shopping.cart.models.dtoRecivers.CarritoCompraDTO;
import com.microservice.shopping.cart.models.dtoResponses.ArticuloCarritoDto;
import com.microservice.shopping.cart.models.dtoResponses.CarritoDto;
import com.microservice.shopping.cart.models.dtoResponses.ShowArticulosDto;
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
            throw new IllegalArgumentException("No se ha proporcionado la infromacion necesaria para registrar un articulo");
        } catch(DataAccessException e) {
            throw new DatabaseAccessException("Se ha producido un error al conectarse a la base de datos");
        }
    }

    @Override
    @Transactional
    public String saveShoppingCart(CarritoCompraDTO dataJson) {
        try {
            ArticulosEntity article = articuloDAO.findById(dataJson.getIdArticulo()).get();
            if (dataJson.getCantidad() > article.getCantidad()) {
                throw new MostProductException("El articulo seleccionado no puede tener una cantidad mayor a la registrada, total de articulos disponibles " + article.getCantidad());
            }

            CarritoCompraEntity newCarrito = carritoCompraDAO.save( new CarritoCompraEntity(
                dataJson.getCantidad(),
                dataJson.getIdArticulo()
                ) 
            );
            article.setCantidad((article.getCantidad() - newCarrito.getCantidad()));
            return "Se ha registrado correctamente el articulo en el carrito";
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("No se ha proporcionado la infromacion necesaria para registrar un articulo en el carrito de compra");
        } catch(DataAccessException e) {
            throw new DatabaseAccessException("Se ha producido un error al conectarse a la base de datos");
        }
    }

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
    @Transactional(readOnly = true)
    public CarritoDto findCarritoCompra(Integer numPagina, Integer tamPagina) {
        try {
            List<Object[]> query = carritoCompraDAO.findArticulosICarrito();
            if (query.isEmpty()) {
                throw new DataNotFound("No hay articulos en el carrito");
            }

            List<ArticuloCarritoDto> listArticulos = new ArrayList<>();
            double costoTotal = 0.0;

            for (Object[] objects : query) {
                // Utiliza métodos de conversión segura proporcionados por Number
                double precio = ((Number) objects[4]).doubleValue();
                double cantidad = ((Number) objects[5]).doubleValue();

                double costoProducto = precio * cantidad;

                ArticuloCarritoDto articulo = new ArticuloCarritoDto(
                        ((Number) objects[0]).intValue(),
                        ((Number) objects[1]).intValue(),
                        (String) objects[2],
                        (String) objects[3],
                        cantidad,
                        precio,
                        costoProducto);

                listArticulos.add(articulo);
                costoTotal += costoProducto;
            }

            Pageable pageable = PageRequest.of(numPagina, tamPagina);
            int startIndex = (int) pageable.getOffset();
            int endIndex = Math.min((startIndex + pageable.getPageSize()), listArticulos.size());

            List<ArticuloCarritoDto> subLista = listArticulos.subList(startIndex, endIndex);
            Page<ArticuloCarritoDto> page = new PageImpl<>(subLista, pageable, listArticulos.size());

            return new CarritoDto(page, costoTotal);
        } catch (DataAccessException e) {
            throw new DatabaseAccessException("Error al conectarse a la base de datos", e);
        }
    }
    

    @Override
    @Transactional
    public void deleteShoppingCart() {
        try {
            carritoCompraDAO.deleteAll();
        } catch(DataAccessException e) {
            throw new DatabaseAccessException("Se ha producido un error al conectarse a la base de datos");
        }
    }

    
    
}
