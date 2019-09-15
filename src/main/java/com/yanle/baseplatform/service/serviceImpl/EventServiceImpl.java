package com.yanle.baseplatform.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.yanle.baseplatform.entity.Event;
import com.yanle.baseplatform.repository.EventRepository;
import com.yanle.baseplatform.service.EventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> findList(
            String platform,
            String page,
            String size
    ) {
        List<Event> eventList;
        try {
            if (platform == null) {
                PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(size));
                eventList = eventRepository.findAll().stream().map(event -> {
                    Event tempEvent = new Event();
                    BeanUtils.copyProperties(event, tempEvent);
                    tempEvent.setId(null);
                    return tempEvent;
                }).collect(Collectors.toList());
                return eventList;
            } else {
                PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(size));
                eventList = eventRepository.findListByPlatform(platform);
                return eventList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
