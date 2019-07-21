package com.tygq.demo02.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户实体类
 */
@Entity
@Data
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;
    @Column(name = "class_id")
    private Integer classId;
}
