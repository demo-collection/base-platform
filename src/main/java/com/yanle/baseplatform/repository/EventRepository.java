package com.yanle.baseplatform.repository;

import com.yanle.baseplatform.data.qo.CreateEvent;
import com.yanle.baseplatform.entity.Event;
import com.yanle.baseplatform.mapper.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "eventRepository")
public class EventRepository {
    @Autowired
    private EventMapper eventMapper;

    public List<Event> findAll() {
        return eventMapper.findAll();
    }

    public void createEvent(CreateEvent createEvent) {
        eventMapper.createEvent(createEvent);
    }
}
