package com.tygq.demo04.dao;

import com.tygq.demo04.entity.GeneralDataTransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralDataTransactionStatusDao  extends JpaRepository<GeneralDataTransactionStatus,String> {
    GeneralDataTransactionStatus findByWorkid(String workid);
    GeneralDataTransactionStatus findByCorrelateid(String correlateid);
}
