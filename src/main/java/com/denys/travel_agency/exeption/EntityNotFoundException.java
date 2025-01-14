package com.denys.travel_agency.exeption;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends Exception{
    private final String name;
    public EntityNotFoundException(String name) {
        this.name = name;
    }
    @Override
    public String getMessage(){
        return name;
    }
}
