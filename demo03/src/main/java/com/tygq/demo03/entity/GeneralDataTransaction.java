package com.tygq.demo03.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "c_general_data_transaction")
@Data
public class GeneralDataTransaction {
    @Id
    private String workid;
    private String systemid;
    private String correlateid;
    private int worktype;
    private int pubstatus;
    private String areano;
    private String topic;
    private String data;
    private String datamd5;
    private int datatype;
    private Date pubtime;
}
