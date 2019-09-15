package com.yanle.baseplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.yanle.baseplatform.data.BaseResponse;
import com.yanle.baseplatform.entity.Event;
import com.yanle.baseplatform.repository.EventRepository;
import com.yanle.baseplatform.utils.JsonRequest;
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
     * @param request request
     * @return response
     */
    @PutMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse create(HttpServletRequest request) {
        try {
            String requestString = JsonRequest.getPayload(request);
            JSONObject object = JSONObject.parseObject(requestString);
            return BaseResponse.responseSuccess(object, "success");
        } catch (Exception e) {
            return BaseResponse.responseError("error");
        }

    }


//    @GetMapping("/page")
//    public BaseResponse selectUser(@RequestParam("id") int id) {
//        PageHelper.startPage(2, 3);
//        PageHelper.orderBy("name DESC");
//        List<Event> eventList = eventRepository.findAll();
//        System.out.println(eventList.toString());
//        for (int i = 0; i < eventList.size() ; i++) {
//            System.out.println(eventList.get(i));
//        }
//        return BaseResponse.responseSuccess(eventList, "success");
//    }

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
