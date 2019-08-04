package com.tygq.demo04.kafka;

import com.alibaba.fastjson.JSONObject;
import com.tygq.demo04.dao.GeneralDataTransactionStatusDao;
import com.tygq.demo04.entity.GeneralDataTransactionStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * 用于接收回执工单
 */
@Component
@Slf4j
public class AutomaticResolveServiceDataUtil {
    public static AutomaticResolveServiceDataUtil automaticResolveServiceDataUtil;

    @Autowired
    private GeneralDataTransactionStatusDao generalDataTransactionStatusDao;

    @PostConstruct
    public void init(){
        automaticResolveServiceDataUtil = this;
    }

    public void receiveData(String jsonStr) {
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject(jsonStr);
        }catch (Exception e){
            log.error("回执的数据不是标准数据,{}",e.getMessage());
            return;
        }
        //1、获取work_id
        String work_id = jsonObject.getString("work_id");
        //2、获取correlate_id
        String correlate_id = jsonObject.getString("correlate_id");
        //3、获取area_no
        String area_no = jsonObject.getString("area_no");

        JSONObject data = jsonObject.getJSONObject("data");
        //4、获取status
        int status = data.getIntValue("result");
        //5、获取描述
        String result_description = data.getString("result_description");

        GeneralDataTransactionStatus generalDataTransactionStatus = new GeneralDataTransactionStatus();
        generalDataTransactionStatus.setWorkid(work_id);
        generalDataTransactionStatus.setCorrelateid(correlate_id);
        generalDataTransactionStatus.setAreano(area_no);
        generalDataTransactionStatus.setStatus(status);
        generalDataTransactionStatus.setFeedbackdate(new Date());
        generalDataTransactionStatus.setResultdescription(result_description);
        try {
            //判断work_id是否存在
            if(automaticResolveServiceDataUtil.generalDataTransactionStatusDao.findByWorkid(work_id) == null){
                automaticResolveServiceDataUtil.generalDataTransactionStatusDao.save(generalDataTransactionStatus);
            }
        }catch (Exception e){
            log.error("处理回执数据出现问题:{}",e.getMessage());
        }
        log.info("处理回执数据结束");

    }


}
