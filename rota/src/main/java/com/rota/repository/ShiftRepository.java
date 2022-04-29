package com.rota.repository;

import com.rota.entity.Shift;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import static io.micronaut.data.model.query.builder.sql.Dialect.H2;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = H2)
public interface ShiftRepository extends CrudRepository<Shift, Long> {
    
   
}
  