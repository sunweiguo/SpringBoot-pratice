package com.tygq.demo04.dao;

import com.tygq.demo04.entity.GeneralDataTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralDataTransactionDao extends JpaRepository<GeneralDataTransaction,String> {
    GeneralDataTransaction findByWorkid(String workid);

}
