package de.artsem.springcourse.Project3REST.dto;

import de.artsem.springcourse.Project3REST.models.Sensor;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class MeasurementDTO {
    //TODO
    @Column(name = "value")
//    @NotEmpty(message = "Value should not be empty")
//    @Size(min = -100, max = 100, message = "Value should be between -100 and 100 characters")
    private double value;

    @Column(name = "raining")
//    @NotEmpty(message = "Raining value should not be empty")
    private boolean raining;

//    @NotEmpty(message = "Sensor value should not be empty")
    @ManyToOne
    @JoinColumn(name="sensor_id",referencedColumnName = "id")
    private Sensor sensor;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
