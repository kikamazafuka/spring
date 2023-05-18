package de.artsem.springcourse.Project3REST.services;

import de.artsem.springcourse.Project3REST.models.Measurement;
import de.artsem.springcourse.Project3REST.repositories.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }

    @Transactional
    public void save(Measurement measurement){
        measurementsRepository.save(measurement);
    }
}
