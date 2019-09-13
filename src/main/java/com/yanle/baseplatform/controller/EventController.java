package com.yanle.baseplatform.controller;

import com.yanle.baseplatform.data.BaseResponse;
import com.yanle.baseplatform.repository.EventRepository;
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
    private EventRepository eventRepository;

    @GetMapping("/list")
    public BaseResponse list() {
        List<Event> list = eventRepository.findAll();
        List<Event> eventList = list.stream().map(event -> {
            Event tempEvent = new Event();
            BeanUtils.copyProperties(event, tempEvent);
            tempEvent.setId(null);
            return tempEvent;
        }).collect(Collectors.toList());

        return BaseResponse.responseSuccess(eventList, "请求成功");
    }

    @GetMapping("/list/error")
    public BaseResponse errorList() {
        return BaseResponse.responseError("error");
    }
}
