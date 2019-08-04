package com.tygq.demo04.vo;

import lombok.Data;

@Data
public class TopicConstructor {
    private String work_id;
    private String correlate_id;
    private String system_id;
    private String area_no;
    private Integer work_type;
    private String topics;//工单发布topic，若发布多个，则用英文逗号[,]分开
    private String pub_time;//工单发布至topic时间[yyyyMMddHHmmss]
    private TopicData data;
    private String data_md5;
}
