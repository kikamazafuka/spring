package de.artsem.springcourse.Project3REST.util;


import de.artsem.springcourse.Project3REST.dto.SensorDTO;
import de.artsem.springcourse.Project3REST.services.SensorsService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorsService sensorsService;

    public SensorValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        SensorDTO sensor = (SensorDTO) target;

        if (sensorsService.findBySensorName(sensor.getName()).isPresent()){
            errors.rejectValue("name", "", "This name has already taken");
        }

    }
}
