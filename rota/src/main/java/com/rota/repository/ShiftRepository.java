package com.rota.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.rota.entity.Shift;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Join.Type;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import static io.micronaut.data.model.query.builder.sql.Dialect.H2;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = H2)
public interface ShiftRepository extends CrudRepository<Shift, Long> {
   @Override
   @Join(value = "chef", type=Type.FETCH)
   @Query("SELECT shift_.`shift_id`,shift_.`date_of`,shift_.`start_time`,shift_.`end_time`,shift_.`break_duration_h`,shift_.`chef_id`,shift_chef_.`f_name` AS chef_f_name,shift_chef_.`l_name` AS chef_l_name,shift_chef_.`h_wage` AS chef_h_wage,shift_chef_.`contact_no` AS chef_contact_no FROM `shift` shift_ INNER JOIN `chef` shift_chef_ ON shift_.`chef_id`=shift_chef_.`chef_id` WHERE (shift_.`shift_id` = :id)")
   Optional<Shift> findById(Long id);

   @Transactional
   @Join(value = "chef", type=Type.FETCH)
   @Query("SELECT shift_.`shift_id`,shift_.`date_of`,shift_.`start_time`,shift_.`end_time`,shift_.`break_duration_h`,shift_.`chef_id`,shift_chef_.`f_name` AS chef_f_name,shift_chef_.`l_name` AS chef_l_name,shift_chef_.`h_wage` AS chef_h_wage,shift_chef_.`contact_no` AS chef_contact_no FROM `shift` shift_ INNER JOIN `chef` shift_chef_ ON shift_.`chef_id`=shift_chef_.`chef_id` WHERE (shift_.`date_of` >= :startDate AND shift_.`date_of` <= :endDate)")
   List<Shift> findAllByDateOfBetween(LocalDate startDate, LocalDate endDate);
}
  