package com.rota.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import io.micronaut.data.annotation.GeneratedValue;
import static io.micronaut.data.annotation.GeneratedValue.Type.AUTO;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

@MappedEntity
public class Shift {

    @Id
    @GeneratedValue(AUTO)
    private Long shift_id;

    @NotNull
    private LocalDate dateOf;

    @NotNull
    private LocalTime start_time;

    @NotNull
    private LocalTime end_time;

    @NotNull
    private Double break_duration_h;

    @NotNull
    private Chef chef;

    public Shift(Long shift_id, LocalDate dateOf, LocalTime start_time, LocalTime end_time, Double break_duration_h, Chef chef) {
        this.shift_id = shift_id;
        this.dateOf = dateOf;
        this.start_time = start_time;
        this.end_time = end_time;
        this.break_duration_h = break_duration_h;
        this.chef = chef;
    }
    
    public Shift() {}
    
    public Long getShift_id() {
        return shift_id;
    }

    public void setShift_id(Long shift_id) {
        this.shift_id = shift_id;
    }

    public LocalDate getDateOf() {
        return dateOf;
    }

    public void setDateOf(LocalDate date_of) {
        this.dateOf = date_of;
    }

    public LocalTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }

    public LocalTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }

    public Double getBreak_duration_h() {
        return break_duration_h;
    }

    public void setBreak_duration_h(Double break_duration_h) {
        this.break_duration_h = break_duration_h;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }


}