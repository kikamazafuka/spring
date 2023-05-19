package de.artsem.springcourse.Project3REST.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name="Measurement")
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
//    @NotEmpty(message = "Value should not be empty")
//    @Size(min = -100, max = 100, message = "Value should be between -100 and 100 characters")
    private double value;

    @Column(name = "raining")
//    @NotEmpty(message = "Raining value should not be empty")
    private boolean raining;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

//    @NotEmpty(message = "Sensor value should not be empty")
    @ManyToOne
    @JoinColumn(name="sensor_id",referencedColumnName = "id")
    private Sensor measurementSensor;

    public Measurement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Sensor getMeasurementSensor() {
        return measurementSensor;
    }

    public void setMeasurementSensor(Sensor measurementSensor) {
        this.measurementSensor = measurementSensor;
    }
}
