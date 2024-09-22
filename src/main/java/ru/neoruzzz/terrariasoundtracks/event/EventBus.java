package ru.neoruzzz.terrariasoundtracks.event;

import ru.neoruzzz.terrariasoundtracks.listeners.Listener;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class EventBus {
    public ArrayList<Listener> listeners = new ArrayList<>();

    public void registry(Listener listener) {
        listeners.add(listener);
    }

    public void remove(Listener listener) {
        listeners.remove(listener);
    }

    public void dispatch(Event event) {
        for (Listener listener : listeners) {
            for (Method method : listener.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(EventHandler.class)) {
                    EventHandler eventHandler = method.getAnnotation(EventHandler.class);
                    if (eventHandler.value().equals(event.getClass())) {
                        try {
                            method.invoke(listener, event);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
