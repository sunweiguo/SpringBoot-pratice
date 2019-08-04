package com.tygq.demo04.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.tygq.demo04.constants.Constant;
import com.tygq.demo04.dao.GeneralDataTransactionDao;
import com.tygq.demo04.entity.GeneralDataTransaction;
import com.tygq.demo04.kafka.KafkaSender;
import com.tygq.demo04.service.IGeneralDataTransactionService;
import com.tygq.demo04.utils.DigestUtil;
import com.tygq.demo04.utils.ResultVOUtil;
import com.tygq.demo04.utils.SequenceGenUtil;
import com.tygq.demo04.vo.MessageVO;
import com.tygq.demo04.vo.ResultVO;
import com.tygq.demo04.vo.TopicConstructor;
import com.tygq.demo04.vo.TopicData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Slf4j
public class GeneralDataTransactionServiceImpl implements IGeneralDataTransactionService {
    @Value("${system.id}")
    private String systemId;
    @Value("${area.no}")
    private String areaNo;
    @Value("${producer.group.id}")
    private String producerGroupId;

    @Autowired
    private GeneralDataTransactionDao generalDataTransactionDao;
    @Autowired
    private KafkaSender kafkaSender;

    @Override
    public Page<GeneralDataTransaction> findAll(PageRequest request) {
        return generalDataTransactionDao.findAll(request);
    }

    @Override
    @Transactional
    public ResultVO sendMessage(MessageVO messageVO) {
        //数据入库
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject(messageVO.getSendmsg());
        }catch (Exception e){
            log.error("进来的数据不是标准数据，解析失败....{}",messageVO.getSendmsg());
            return ResultVOUtil.error(Constant.ERROR,"输入的不是标准json格式...");
        }
        try {
            //判断workid是否已经存在
            GeneralDataTransaction g = generalDataTransactionDao.findByWorkid(jsonObject.getString("work_id"));
            if(g != null){
                log.error("该workid={}已经存在",jsonObject.getString("work_id"));
                return ResultVOUtil.error(Constant.ERROR,"workid已经存在，数据需要重新更换");
            }
            GeneralDataTransaction generalDataTransaction = new GeneralDataTransaction();
            generalDataTransaction.setWorkid(jsonObject.getString("work_id"));
            generalDataTransaction.setSystemid(jsonObject.getString("system_id"));
            generalDataTransaction.setCorrelateid(jsonObject.getString("correlate_id"));
            generalDataTransaction.setWorktype(jsonObject.getIntValue("work_type"));//0标识默认工单
            generalDataTransaction.setPubstatus(0);//0标识下发正常
            generalDataTransaction.setAreano(jsonObject.getString("area_no"));
            generalDataTransaction.setTopic(producerGroupId);
            generalDataTransaction.setData(messageVO.getSendmsg());//TODO 这里就不提取出实际的data了，直接将整个塞进去
            generalDataTransaction.setDatamd5(DigestUtil.MD5(messageVO.getSendmsg()));//TODO 对整个加密
            generalDataTransaction.setPubtime(new Date());
            generalDataTransactionDao.save(generalDataTransaction);
        }catch (Exception e){
            log.error("解析json数据出现问题或者插入数据库出现问题:{}",e.getMessage());
            return ResultVOUtil.error(Constant.ERROR,"解析json数据出现问题或者插入数据库出现问题");
        }

        //发送消息
        try {
            kafkaSender.send(messageVO);
        } catch (JsonProcessingException e) {
            return ResultVOUtil.error(Constant.ERROR,"下发kafka失败");
        }
        return ResultVOUtil.success("下发kafka成功");
    }
}
