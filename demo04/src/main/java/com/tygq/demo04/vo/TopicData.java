package com.tygq.demo04.vo;

import lombok.Data;

import java.util.Map;

@Data
public class TopicData {
    private Integer data_type;
    private String database;
    private String table;
    private Map<String,String> extra;
    private Map<String,Object> primaries;
    private Map<String,Object> attrs;
}
