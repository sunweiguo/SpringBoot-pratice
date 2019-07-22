package com.tygq.demo03.dao;

import com.tygq.demo03.entity.GeneralDataTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralDataTransactionDao extends JpaRepository<GeneralDataTransaction,String> {

}
