package com.microservice.shopping.cart.models.repositorys;

import org.springframework.data.repository.CrudRepository;

import com.microservice.shopping.cart.models.entitys.CarritoCompraEntity;

public interface CarritoCompraDAO extends CrudRepository<CarritoCompraEntity, Integer>{
    
}
