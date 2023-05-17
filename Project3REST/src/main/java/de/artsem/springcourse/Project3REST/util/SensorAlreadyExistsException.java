package de.artsem.springcourse.Project3REST.util;

public class SensorAlreadyExistsException extends RuntimeException{

    public SensorAlreadyExistsException(String msg){
        super(msg);
    }
}
