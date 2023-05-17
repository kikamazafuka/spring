package de.artsem.springcourse.Project3REST.services;

import de.artsem.springcourse.Project3REST.models.Sensor;
import de.artsem.springcourse.Project3REST.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorsService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Sensor sensor){

        sensorRepository.save(sensor);
    }


    public Optional<Sensor> findBySensorName(String name){
        System.out.println("SensorService find by sensor name before");
        Optional<Sensor> foundedSensor = sensorRepository.findByName(name);
        return foundedSensor;
    }
}
