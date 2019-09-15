package com.yanle.baseplatform.repository;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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

    public List<Event> findListByPlatform(String platform) {
        return eventMapper.findListByPlatform(platform);
    }

    public Page<Event> query(int page) {
        PageHelper.startPage(page, 2);
        return eventMapper.query();
    }
}
