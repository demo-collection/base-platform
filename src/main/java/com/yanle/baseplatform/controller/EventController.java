package com.yanle.baseplatform.controller;

import com.yanle.baseplatform.data.BaseResponse;
import com.yanle.baseplatform.entity.Event;
import com.yanle.baseplatform.repository.EventRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
     * @param request
     * @return
     */
    @PutMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse create(HttpServletRequest request) {
        ServletInputStream inputStream;
        try {
            inputStream = request.getInputStream();
            int nRead = 1;
            int nTotalRead = 0;
            byte[] bytes = new byte[1024 * 10];
            while (nRead > 0) {
                nRead = inputStream.read(bytes, nTotalRead, bytes.length - nTotalRead);
                if (nRead > 0) {
                    nTotalRead = nTotalRead + nRead;
                }
            }
            String str = new String(bytes, 0, nTotalRead, StandardCharsets.UTF_8);
            return BaseResponse.responseSuccess(str, "success");
        } catch (IOException e) {
            e.printStackTrace();
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
