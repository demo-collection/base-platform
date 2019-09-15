package com.yanle.baseplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.yanle.baseplatform.data.BaseResponse;
import com.yanle.baseplatform.data.qo.CreateEvent;
import com.yanle.baseplatform.entity.Event;
import com.yanle.baseplatform.repository.EventRepository;
import com.yanle.baseplatform.utils.JsonRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/event")
@Slf4j
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    /**
     * 列表查询
     *
     * @return response
     */
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

    /**
     * 做一个接受请求payload 实验
     *
     * @param request request
     * @return response
     */
    @PutMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse create(HttpServletRequest request) {
        CreateEvent createEvent;
        try {
            String requestString = JsonRequest.getPayload(request);
            createEvent = JSONObject.parseObject(requestString, CreateEvent.class);
            log.info(createEvent.toString());
            eventRepository.createEvent(createEvent);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.responseError(e.getMessage());
        }

        return BaseResponse.responseSuccess(null, "success");
    }

    /**
     * 抛错误测试
     *
     * @return response
     */
    @GetMapping("/list/error")
    public BaseResponse errorList() {
        return BaseResponse.responseError("error");
    }
}
