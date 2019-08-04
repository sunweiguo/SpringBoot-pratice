package com.tygq.demo04.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "c_general_data_transaction_status")
@Data
public class GeneralDataTransactionStatus {
    @Id
    private String workid;
    private String correlateid;
    private Integer status;
    private String areano;
    private Date feedbackdate;
    private String resultdescription;
}
