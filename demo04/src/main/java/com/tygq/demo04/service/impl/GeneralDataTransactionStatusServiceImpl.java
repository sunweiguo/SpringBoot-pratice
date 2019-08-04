package com.tygq.demo04.service.impl;

import com.tygq.demo04.constants.Constant;
import com.tygq.demo04.dao.GeneralDataTransactionStatusDao;
import com.tygq.demo04.entity.GeneralDataTransactionStatus;
import com.tygq.demo04.service.IGeneralDataTransactionStatusService;
import com.tygq.demo04.utils.ResultVOUtil;
import com.tygq.demo04.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GeneralDataTransactionStatusServiceImpl implements IGeneralDataTransactionStatusService {
    @Autowired
    private GeneralDataTransactionStatusDao generalDataTransactionStatusDao;

    @Override
    public Page<GeneralDataTransactionStatus> findAll(PageRequest request) {
        return generalDataTransactionStatusDao.findAll(request);
    }

    @Override
    public GeneralDataTransactionStatus findByCorrelateid(String correlateid) {
        return generalDataTransactionStatusDao.findByCorrelateid(correlateid);
    }
}
