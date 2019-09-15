package com.yanle.baseplatform.service;

import com.yanle.baseplatform.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> findList(String platform, String page, String size);
}
