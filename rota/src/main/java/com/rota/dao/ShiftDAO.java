package com.rota.dao;

import java.time.LocalDate;
import java.time.LocalTime;

import com.rota.entity.Chef;

public class ShiftDAO {

    private Long shift_id;

    private LocalDate dateOf;

    private LocalTime startTime;

    private LocalTime endTime;

    private Double breakDurationInHours;

    private Chef chef;

    public ShiftDAO (Long shift_id, LocalDate dateOf, LocalTime startTime, LocalTime endTime, Double breakDurationInHours, Chef chef) {
        this.shift_id = shift_id;
        this.dateOf = dateOf;
        this.startTime = startTime;
        this.endTime = endTime;
        this.breakDurationInHours = breakDurationInHours;
        this.chef = chef;
    }
    
    public ShiftDAO() {}
    
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