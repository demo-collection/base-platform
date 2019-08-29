package com.yanle.baseplatform.repository;

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
}
