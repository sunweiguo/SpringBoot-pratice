package com.tygq.demo04.service;

import com.tygq.demo04.entity.GeneralDataTransaction;
import com.tygq.demo04.vo.MessageVO;
import com.tygq.demo04.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IGeneralDataTransactionService {
    Page<GeneralDataTransaction> findAll(PageRequest request);

    ResultVO sendMessage(MessageVO messageVO);
}
