package com.tygq.demo04.service;

import com.tygq.demo04.entity.GeneralDataTransactionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IGeneralDataTransactionStatusService {
    Page<GeneralDataTransactionStatus> findAll(PageRequest request);

    GeneralDataTransactionStatus findByCorrelateid(String correlateid);
}
