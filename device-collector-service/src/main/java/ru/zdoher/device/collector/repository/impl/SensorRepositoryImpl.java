package ru.zdoher.device.collector.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.zdoher.device.collector.domain.ExtendedSensor;
import ru.zdoher.device.collector.repository.SensorRepository;
import ru.zdoher.device.collector.repository.jpa.SensorJpaRepository;
import ru.zdoher.device.collector.repository.mapper.SensorEntityMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SensorRepositoryImpl implements SensorRepository {

    private final SensorJpaRepository sensorJpaRepository;
    private final SensorEntityMapper sensorEntityMapper;

    @Override
    public ExtendedSensor save(ExtendedSensor extendedSensor) {
        return sensorEntityMapper.to(
          sensorJpaRepository.save(
            sensorEntityMapper.from(
              extendedSensor
            )
          )
        );
    }

    @Override
    public List<ExtendedSensor> saveAll(List<ExtendedSensor> extendedSensors) {
        return sensorJpaRepository.saveAll(
            extendedSensors.stream()
              .map(sensorEntityMapper::from)
              .toList()
          )
          .stream()
          .map(sensorEntityMapper::to)
          .toList();
    }
}
