package de.semart.sprigcourse;

public class RapMusic implements Music{

    public void beanInit(){
        System.out.println("Bean Init");
    }

    public void beanDestroy(){
        System.out.println("Bean Destroy");
    }
    @Override
    public String getSong(){
        return "Kasta";
    }
}
