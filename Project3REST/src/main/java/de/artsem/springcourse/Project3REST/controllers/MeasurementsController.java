package de.artsem.springcourse.Project3REST.controllers;

import de.artsem.springcourse.Project3REST.dto.MeasurementDTO;
import de.artsem.springcourse.Project3REST.dto.SensorDTO;
import de.artsem.springcourse.Project3REST.models.Measurement;
import de.artsem.springcourse.Project3REST.models.Sensor;
import de.artsem.springcourse.Project3REST.services.MeasurementsService;
import de.artsem.springcourse.Project3REST.services.SensorsService;
import de.artsem.springcourse.Project3REST.util.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;
    private final SensorsService sensorsService;
    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;

    private final SensorController sensorController;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, SensorsService sensorsService, SensorValidator sensorValidator, ModelMapper modelMapper, SensorController sensorController) {
        this.measurementsService = measurementsService;
        this.sensorsService = sensorsService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
        this.sensorController = sensorController;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody  @Valid MeasurementDTO measurementDTO,
                                             BindingResult bindingResult){

//        bindingResult.hasErrors();

        //TODO check for errors

        Measurement measurement = convertToMeasurement(measurementDTO);
        Optional<Sensor> fullSensor= sensorsService.findBySensorName(measurement.getSensor().getName());
//        sensorValidator.validate(fullSensor.get(),bindingResult);
        StringBuilder errorMsg = new StringBuilder();
        try {
            if (bindingResult.hasErrors()){
                List<FieldError> errors = bindingResult.getFieldErrors();
                for (FieldError error : errors){
                    errorMsg.append(error.getField())
                            .append(" - ").append(error.getDefaultMessage())
                            .append(";");
                }
                throw new MeasurementNoSuchSensorException(errorMsg.toString());

            }
            measurement.setSensor(fullSensor.get());
            measurementsService.save(measurement);

        }
        catch (Exception e){
            throw new MeasurementNoSuchSensorException("No such sensor");
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public List<MeasurementDTO> getMeasurements(){
        //TODO show Sensor in response
        List<Measurement> allMeasurements = measurementsService.getAllMeasurements();
        List<MeasurementDTO> measurementDTOList = new ArrayList<>();
        for (Measurement measurement : allMeasurements){
            MeasurementDTO measurementDTO= convertToMeasurementDTO(measurement);
            measurementDTOList.add(measurementDTO);
        }
//       return measurementsService.getAllMeasurements().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList());
        return measurementDTOList;
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDaysCount(){
//        measurementsService.getAllRainyDays(true);
        return measurementsService.getAllRainyDays(true);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNoSuchSensorException e){
        MeasurementErrorResponse measurementErrorResponse = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(measurementErrorResponse, HttpStatus.BAD_REQUEST);
    }

    //TODO Exceptions handling methods
    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        MeasurementDTO measurementDTO = new MeasurementDTO();
        measurementDTO.setValue(measurement.getValue());
        measurementDTO.setSensorDTO(convertToSensorDTO(measurement.getSensor()));
//        enrichMeasurement(measurement);
//        return modelMapper.map(measurement, MeasurementDTO.class);
        return measurementDTO;
    }

    private SensorDTO convertToSensorDTO(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }

    private void enrichMeasurement(Measurement measurement) {
        MeasurementDTO measurementDTO = new MeasurementDTO();
        measurementDTO.setSensorDTO(convertToSensorDTO(measurement.getSensor()));
    }


}
