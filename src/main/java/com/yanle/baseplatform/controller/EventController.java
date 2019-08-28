package com.yanle.baseplatform.controller;

import com.yanle.baseplatform.dao.EventDao;
import com.yanle.baseplatform.entity.Event;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EventController {
    @Autowired
    private EventDao eventDao;

    @GetMapping("/list")
    public List<Event> list() {
        List<Event> list = eventDao.findAll();

        List<Event> eventList = list.stream().map(event -> {
            Event tempEvent = new Event();
            BeanUtils.copyProperties(event, tempEvent);
            tempEvent.setId(null);
            return tempEvent;
        }).collect(Collectors.toList());

        return eventList;
    }
}
