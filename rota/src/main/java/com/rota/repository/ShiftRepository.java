package com.rota.repository;

import java.time.LocalDate;
import java.util.List;

import com.rota.entity.Shift;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import static io.micronaut.data.model.query.builder.sql.Dialect.H2;
import io.micronaut.data.repository.PageableRepository;

@JdbcRepository(dialect = H2)
public interface ShiftRepository extends PageableRepository<Shift, Long> {
    
    @Join(value = "chef", type = Join.Type.FETCH)
    List<Shift> getByDateOfInList(List<LocalDate> dateOf);
    @Join(value = "chef", type = Join.Type.FETCH)
    List<Shift> findAllByDateOfBetween(LocalDate start, LocalDate finish);
    // TODO remove colons
    @Query(value= "INSERT INTO shift(`date_of`, `start_time`, `end_time`, `break_duration_h`, `chef`) VALUES(`entity.getDateOf()`,`entity.getStartTime()`,`entity.getEndTime()`,`entity.getBreakDurationInHours()`,`entity.getChef().getChef_id()`",nativeQuery=true)
    void insertNativeOne(Shift entity);
}
  