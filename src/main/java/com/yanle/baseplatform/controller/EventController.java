package com.yanle.baseplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.yanle.baseplatform.data.BaseResponse;
import com.yanle.baseplatform.data.qo.CreateEvent;
import com.yanle.baseplatform.entity.Event;
import com.yanle.baseplatform.repository.EventRepository;
import com.yanle.baseplatform.service.EventService;
import com.yanle.baseplatform.utils.JsonRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/event")
@Slf4j
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    /**
     * 列表查询
     *
     * @return response
     */
    @GetMapping("/list")
    public BaseResponse list(
            @RequestParam(name = "platform", required = false) String platform,
            @RequestParam(name = "page", defaultValue = "1") String page,
            @RequestParam(name = "size", defaultValue = "10") String size
    ) {
        List<Event> list = eventService.findList(platform, page, size);
        if (list != null) {
            System.out.println(list.size());
            return BaseResponse.responseSuccess(list, "请求成功");
        }
        return BaseResponse.responseError("请求失败");
    }

    /**
     * 分页测试
     * @param page
     * @return
     */
    @GetMapping("/list_page")
    public Page<Event> page(
            @RequestParam int page
    ) {
        return eventRepository.query(page);
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
