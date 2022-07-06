package com.Tienda.Tienda.repository;

import com.Tienda.Tienda.entity.Pais;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PaisRepository extends CrudRepository<Pais, Long> {

}
