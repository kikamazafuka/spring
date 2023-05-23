package de.artsem.springcourse.Project3REST.services;

import de.artsem.springcourse.Project3REST.models.Measurement;
import de.artsem.springcourse.Project3REST.models.Sensor;
import de.artsem.springcourse.Project3REST.repositories.MeasurementsRepository;
import de.artsem.springcourse.Project3REST.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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

        measurement.setCreatedAt(LocalDateTime.now());
        measurementsRepository.save(measurement);
    }

    public List<Measurement> getAllMeasurements(){
        List<Measurement> allMeasurements =measurementsRepository.findAll();
        return allMeasurements;
    }

    @Transactional
    public void assignSensorToMeasurement(int id, Sensor assignedSensor){
        Optional<Measurement> measurementOptional = measurementsRepository.findById(id);
        if (measurementOptional.isPresent()){
//            boolean outOfTime = false;
            Measurement measurement = measurementOptional.get();
//            measurement.setCreatedAt(LocalDateTime.now());

            measurement.setSensor(assignedSensor);
            measurementsRepository.save(measurement);
        }
    }

    public int getAllRainyDays(boolean raining) {
        List<Measurement> rainyDays = measurementsRepository.findAllByRaining(raining);

        int count = 0;
        for (Measurement rain : rainyDays){
            if (rain.isRaining()){
                count++;
            }
        }
        return count;
    }
}
