package com.yanle.baseplatform.mapper;

import com.yanle.baseplatform.data.qo.CreateEvent;
import com.yanle.baseplatform.entity.Event;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "eventMapper")
public interface EventMapper {

    List<Event> findAll();

    void createEvent(CreateEvent createEvent);
}
