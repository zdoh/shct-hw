package ru.zdoher.device.collector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zdoher.device.collector.domain.ReceivedDevice;
import ru.zdoher.device.collector.service.DeviceSaver;
import ru.zdoher.device.collector.service.ReceivedDeviceProcessor;

@Service
@RequiredArgsConstructor
public class ReceivedDeviceProcessingService implements ReceivedDeviceProcessor {

    private final DeviceSaver deviceSaver;

    @Override
    public void process(ReceivedDevice receivedDevice) {
        deviceSaver.save(receivedDevice.device());
    }
}
