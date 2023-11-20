package com.microservice.shopping.cart.models.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.microservice.shopping.cart.models.entitys.CarritoCompraEntity;

public interface CarritoCompraDAO extends CrudRepository<CarritoCompraEntity, Integer>{
    
    @Query("select art.idArticulo, cc.id, art.fotografia, art.description, art.precio, cc.cantidad " +
            "FROM ArticulosEntity art " +
            "INNER JOIN CarritoCompraEntity cc " +
            "ON art.idArticulo = cc.idArticulo")
    public List<Object[]> findArticulosICarrito();

}
