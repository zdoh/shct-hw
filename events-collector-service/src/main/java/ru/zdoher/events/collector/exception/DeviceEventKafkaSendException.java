package ru.zdoher.events.collector.exception;

public class DeviceEventKafkaSendException extends BaseEventCollectorException {

    public DeviceEventKafkaSendException(Throwable exception) {
        super(exception);
    }
}
