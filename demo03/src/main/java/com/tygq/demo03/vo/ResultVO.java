package com.tygq.demo03.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用于统一返回json的封装类
 * @param <T>
 */
@Data
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = 2357154234168492776L;
    private Integer code;
    private String msg;
    private T data;
}
