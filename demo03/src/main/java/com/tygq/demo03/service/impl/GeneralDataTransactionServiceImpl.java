package com.tygq.demo03.service.impl;

import com.tygq.demo03.dao.GeneralDataTransactionDao;
import com.tygq.demo03.entity.GeneralDataTransaction;
import com.tygq.demo03.service.IGeneralDataTransactionService;
import com.tygq.demo03.vo.MessageVO;
import com.tygq.demo03.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GeneralDataTransactionServiceImpl implements IGeneralDataTransactionService {

    @Autowired
    private GeneralDataTransactionDao generalDataTransactionDao;

    @Override
    public Page<GeneralDataTransaction> findAll(PageRequest request) {
        return generalDataTransactionDao.findAll(request);
    }

    @Override
    public ResultVO sendMessage(MessageVO messageVO) {

        return null;
    }
}
