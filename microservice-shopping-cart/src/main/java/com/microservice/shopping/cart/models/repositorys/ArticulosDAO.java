package com.microservice.shopping.cart.models.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.microservice.shopping.cart.models.entitys.ArticulosEntity;

public interface ArticulosDAO  extends CrudRepository<ArticulosEntity, Integer>{
    
    @Query("SELECT a from ArticulosEntity a where a.description like %?1%")
    public List<ArticulosEntity> findArticulesByDescription(String description_word);

}
