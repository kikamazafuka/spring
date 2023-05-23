package de.artsem.springcourse.Project3REST.util;

public class MeasurementNoSuchSensorException extends RuntimeException{
    public MeasurementNoSuchSensorException(String msg){
        super(msg);
    }
}
