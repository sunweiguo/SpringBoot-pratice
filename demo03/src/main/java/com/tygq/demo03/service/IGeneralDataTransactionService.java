package com.tygq.demo03.service;

import com.tygq.demo03.entity.GeneralDataTransaction;
import com.tygq.demo03.vo.MessageVO;
import com.tygq.demo03.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IGeneralDataTransactionService {
    Page<GeneralDataTransaction> findAll(PageRequest request);

    ResultVO sendMessage(MessageVO messageVO);
}
