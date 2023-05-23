package de.artsem.springcourse.Project3REST.controllers;

import de.artsem.springcourse.Project3REST.dto.SensorDTO;
import de.artsem.springcourse.Project3REST.models.Sensor;
import de.artsem.springcourse.Project3REST.services.SensorsService;
import de.artsem.springcourse.Project3REST.util.SensorAlreadyExistsException;
import de.artsem.springcourse.Project3REST.util.SensorErrorResponse;
import de.artsem.springcourse.Project3REST.util.SensorNotCreatedException;
import de.artsem.springcourse.Project3REST.util.SensorValidator;
import jakarta.validation.Valid;
import org.hibernate.NonUniqueResultException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorsService sensorsService;
    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorsService sensorsService, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.sensorsService = sensorsService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO,
                                             BindingResult bindingResult){

        StringBuilder errorMsg = new StringBuilder();
        try {
            if (bindingResult.hasErrors()){
                List<FieldError> errors = bindingResult.getFieldErrors();
                for (FieldError error : errors){
                    errorMsg.append(error.getField())
                            .append(" - ").append(error.getDefaultMessage())
                            .append(";");
                }
                throw new SensorNotCreatedException(errorMsg.toString());
            }
            sensorValidator.validate(sensorDTO,bindingResult);
            sensorsService.save(convertToSensor(sensorDTO));
            return ResponseEntity.ok(HttpStatus.OK);
            //TODO should not create 2 entities
        }
        catch (SensorNotCreatedException sensorNotCreatedException) {
            throw new SensorNotCreatedException(errorMsg.toString());
        }
        catch (Exception existsException){     // should handle definite exception
           throw new SensorAlreadyExistsException("Sensor with this name already exists");
        }
    }


    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e){
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorAlreadyExistsException e){
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }

}
