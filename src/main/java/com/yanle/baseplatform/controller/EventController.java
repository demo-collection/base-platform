package com.yanle.baseplatform.controller;

import com.github.pagehelper.PageHelper;
import com.yanle.baseplatform.data.BaseResponse;
import com.yanle.baseplatform.repository.EventRepository;
import com.yanle.baseplatform.entity.Event;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/page")
    public BaseResponse selectUser(@RequestParam("id") int id) {
        PageHelper.startPage(2, 3);
        PageHelper.orderBy("name DESC");
        List<Event> eventList = eventRepository.findAll();
        System.out.println(eventList.toString());
        for (int i = 0; i < eventList.size() ; i++) {
            System.out.println(eventList.get(i));
        }
        return BaseResponse.responseSuccess(eventList, "success");
    }

    @GetMapping("/list/error")
    public BaseResponse errorList() {
        return BaseResponse.responseError("error");
    }
}
