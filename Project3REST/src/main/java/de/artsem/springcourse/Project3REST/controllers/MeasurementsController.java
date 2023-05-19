package de.artsem.springcourse.Project3REST.controllers;

import de.artsem.springcourse.Project3REST.dto.MeasurementDTO;
import de.artsem.springcourse.Project3REST.models.Measurement;
import de.artsem.springcourse.Project3REST.models.Sensor;
import de.artsem.springcourse.Project3REST.services.MeasurementsService;
import de.artsem.springcourse.Project3REST.services.SensorsService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;
    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, SensorsService sensorsService, ModelMapper modelMapper) {
        this.measurementsService = measurementsService;
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody  @Valid MeasurementDTO measurementDTO,
                                             BindingResult bindingResult){

//        bindingResult.hasErrors();
        //TODO check for errors
        Measurement measurement = convertToMeasurement(measurementDTO);
        Optional<Sensor> fullSensor= sensorsService.findBySensorName(measurement.getMeasurementSensor().getName());
        measurement.setMeasurementSensor(fullSensor.get());
        measurementsService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //TODO Exceptions handling methods
    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }



}
