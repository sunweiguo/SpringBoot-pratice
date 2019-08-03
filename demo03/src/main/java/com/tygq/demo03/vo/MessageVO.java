package com.tygq.demo03.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MessageVO {
    @NotBlank(message = "kafka地址不能为空")
    private String address;
    @NotBlank(message = "kafka端口不能为空")
    private String port;
    @NotBlank(message = "kafka的主题不能为空")
    private String topic;
    @NotBlank(message = "发送的内容不能为空")
    private String sendmsg;
}
