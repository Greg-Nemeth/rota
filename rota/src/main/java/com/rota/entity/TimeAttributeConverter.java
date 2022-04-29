package com.rota.entity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import io.micronaut.core.convert.ConversionContext;
import io.micronaut.data.model.runtime.convert.AttributeConverter;
import jakarta.inject.Singleton;

@Singleton
public class TimeAttributeConverter implements AttributeConverter<LocalTime, String>{
    
    @Override
    public String convertToPersistedValue(LocalTime time, ConversionContext context) {
        return time == null ? null : time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public LocalTime convertToEntityValue(String value, ConversionContext context) {
        return value == null ? null : LocalTime.parse(value, DateTimeFormatter.ISO_LOCAL_TIME);
    }

}