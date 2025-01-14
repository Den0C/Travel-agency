package com.denys.travel_agency.exeption;

public class EntityAlreadyExistsException extends Exception{
    public String getErrorCode() {
        return "DUPLICATE_USERNAME";
    }

    @Override
    public String getMessage(){
        return "This username is already exist";
    }

}
