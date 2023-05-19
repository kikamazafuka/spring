package de.artsem.springcourse.Project3REST.services;

import de.artsem.springcourse.Project3REST.models.Measurement;
import de.artsem.springcourse.Project3REST.models.Sensor;
import de.artsem.springcourse.Project3REST.repositories.MeasurementsRepository;
import de.artsem.springcourse.Project3REST.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorRepository sensorRepository) {
        this.measurementsRepository = measurementsRepository;
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Measurement measurement){

        measurementsRepository.save(measurement);
    }

    @Transactional
    public void assignSensorToMeasurement(int id, Sensor assignedSensor){
        Optional<Measurement> measurementOptional = measurementsRepository.findById(id);
        if (measurementOptional.isPresent()){
//            boolean outOfTime = false;
            Measurement measurement = measurementOptional.get();
//            book.setAssignedAt(LocalDateTime.now());

            measurement.setMeasurementSensor(assignedSensor);
            measurementsRepository.save(measurement);
        }
    }
}
