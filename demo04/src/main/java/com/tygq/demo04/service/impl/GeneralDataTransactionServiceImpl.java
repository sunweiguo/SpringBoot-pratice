package com.tygq.demo04.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tygq.demo04.constants.Constant;
import com.tygq.demo04.dao.GeneralDataTransactionDao;
import com.tygq.demo04.entity.GeneralDataTransaction;
import com.tygq.demo04.kafka.KafkaSender;
import com.tygq.demo04.service.IGeneralDataTransactionService;
import com.tygq.demo04.utils.ResultVOUtil;
import com.tygq.demo04.vo.MessageVO;
import com.tygq.demo04.vo.ResultVO;
import com.tygq.demo04.vo.TopicConstructor;
import com.tygq.demo04.vo.TopicData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class GeneralDataTransactionServiceImpl implements IGeneralDataTransactionService {

    @Autowired
    private GeneralDataTransactionDao generalDataTransactionDao;
    @Autowired
    private KafkaSender kafkaSender;

    @Override
    public Page<GeneralDataTransaction> findAll(PageRequest request) {
        return generalDataTransactionDao.findAll(request);
    }

    @Override
    public ResultVO sendMessage(MessageVO messageVO) {
        try {
            kafkaSender.send(messageVO);
        } catch (JsonProcessingException e) {
            return ResultVOUtil.error(Constant.ERROR,"下发kafka失败");
        }
        return ResultVOUtil.success("下发kafka成功");
    }
}
