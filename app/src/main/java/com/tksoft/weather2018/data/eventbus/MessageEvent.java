package com.tksoft.weather2018.data.eventbus;

public class MessageEvent {
    public Event event;

    public MessageEvent(Event event) {
        this.event = event;
    }
}
