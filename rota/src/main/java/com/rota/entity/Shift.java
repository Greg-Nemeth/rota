package com.rota.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import io.micronaut.data.annotation.GeneratedValue;
import static io.micronaut.data.annotation.GeneratedValue.Type.AUTO;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.data.annotation.Relation.Kind;
import io.micronaut.data.jdbc.annotation.JoinColumn;
import jakarta.persistence.Column;

@MappedEntity
public class Shift {

    @Id
    @GeneratedValue(AUTO)
    private Long shift_id;

    @NotNull
    @Column(name = "date_of")
    private LocalDate dateOf;

    @NotNull
    @Column(name = "start_time")
    private LocalTime startTime;

    @NotNull
    @Column(name = "end_time")
    private LocalTime endTime;

    @NotNull
    @Column(name = "break_duration_h")
    private Double breakDurationInHours;

    @NotNull
    @Column(name= "chef_id")
    @Relation(value=Kind.MANY_TO_ONE)
    @JoinColumn(referencedColumnName="chef")
    private Chef chef;

    public Shift(Long shift_id, LocalDate dateOf, LocalTime startTime, LocalTime endTime, Double breakDurationInHours, Chef chef) {
        this.shift_id = shift_id;
        this.dateOf = dateOf;
        this.startTime = startTime;
        this.endTime = endTime;
        this.breakDurationInHours = breakDurationInHours;
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

    public void setDateOf(LocalDate dateOf) {
        this.dateOf = dateOf;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Double getBreakDurationInHours() {
        return breakDurationInHours;
    }

    public void setBreakDurationInHours(Double breakDurationInHours) {
        this.breakDurationInHours = breakDurationInHours;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }


}