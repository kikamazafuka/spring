package de.semart.sprigcourse;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class RapMusic implements Music{

    public void beanInit(){
        System.out.println("Bean Init");
    }

    public void beanDestroy(){
        System.out.println("Bean Destroy");
    }
    @Override
    public List<String> getSong(){
        return new ArrayList<>();
    }
}
