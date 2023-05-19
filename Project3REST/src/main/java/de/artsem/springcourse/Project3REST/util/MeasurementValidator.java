package de.artsem.springcourse.Project3REST.util;

import de.artsem.springcourse.Project3REST.dto.MeasurementDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MeasurementValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
