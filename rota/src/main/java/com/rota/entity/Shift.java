package com.rota.entity;

import io.micronaut.data.annotation.GeneratedValue;
import static io.micronaut.data.annotation.GeneratedValue.Type.AUTO;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.NotNull;

@MappedEntity
public class Shift {

    @Id
    @GeneratedValue(AUTO)
    private Long shift_id;

    @NotNull
    private LocalDate date_of;

    @NotNull
    private LocalTime start_time;

    @NotNull
    private LocalTime end_time;

    @NotNull
    private Double break_duration_h;

    @NotNull
    private Chef chef;

    public Shift(Long shift_id, LocalDate date_of, LocalTime start_time, LocalTime end_time, Double break_duration_h, Chef chef) {
        this.shift_id = shift_id;
        this.date_of = date_of;
        this.start_time = start_time;
        this.end_time = end_time;
        this.break_duration_h = break_duration_h;
        this.chef = chef;
    }

    public Long getShift_id() {
        return shift_id;
    }

    public void setShift_id(Long shift_id) {
        this.shift_id = shift_id;
    }

    public LocalDate getDate_of() {
        return date_of;
    }

    public void setDate_of(LocalDate date_of) {
        this.date_of = date_of;
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

    // @Override
    // public String toString() {
    //     List<String> attributes = Arrays.asList("Shift id","Chef", "Start time", "End time", "Break duration(H)");
    //     List<Object> valList = Arrays.asList(this.shift_id, (this.chef.getF_name()+" "+this.chef.getL_name()),this.start_time,this.end_time,this.break_duration_h);
    //     String h_border = StringUtils.repeat("-", 35)+"\n";
        
    //     String filling = attributes.stream()
    //         .map(x-> {String temp = "|  "+x+" : " + valList.get(attributes.indexOf(x)).toString();
    //                     String padding = StringUtils.repeat(" ", 34-temp.length()) + "|";
    //                     String rtrn = temp+padding+"\n";
    //                     return rtrn;})
    //         .reduce("",String::concat);
        
    //     String resy = h_border+ filling + h_border;
    //     return resy;

    // }
}