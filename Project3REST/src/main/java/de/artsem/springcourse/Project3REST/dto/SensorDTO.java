package de.artsem.springcourse.Project3REST.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.artsem.springcourse.Project3REST.models.Measurement;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class SensorDTO {


    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

//    @OneToMany(mappedBy = "measurementSensor")
//    @JsonManagedReference
//    private List<Measurement> measurements;

    @OneToMany(mappedBy = "sensor")
    @JsonManagedReference
    @JsonIgnore
    private List<MeasurementDTO> measurements;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Measurement> getMeasurements() {
//        return measurements;
//    }
//
//    public void setMeasurements(List<Measurement> measurements) {
//        this.measurements = measurements;
//    }


    public List<MeasurementDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}
