package com.rota.entity;


import javax.validation.constraints.NotNull;

import io.micronaut.data.annotation.GeneratedValue;
import static io.micronaut.data.annotation.GeneratedValue.Type.AUTO;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

@MappedEntity
public class Position {

    @Id
    @GeneratedValue(AUTO)
    private Long pos_id;

    @NotNull
    private String position;

    public Position(@NotNull String position) {
        this.position = position;
    }

    public Long getPos_id() {
        return pos_id;
    }

    public void setPos_id(Long pos_id) {
        this.pos_id = pos_id;
    }

    public String getPosition() {
        return position;
    }

}