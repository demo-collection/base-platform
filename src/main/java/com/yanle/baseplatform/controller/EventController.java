package com.yanle.baseplatform.controller;

import com.yanle.baseplatform.dao.EventDao;
import com.yanle.baseplatform.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {
    @Autowired
    private EventDao eventDao;

    @GetMapping("/list")
    public List<Event> list() {
        List<Event> list = eventDao.findAll();
        return list;
    }
}
