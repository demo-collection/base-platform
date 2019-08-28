package com.yanle.baseplatform.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Event {
    private Integer id;

    private String platform;

    private String eventKey;

    private String description;

    private Date updateTime;

    private Date createTime;
}
