package com.tygq.demo04.controller;

import com.tygq.demo04.entity.GeneralDataTransactionStatus;
import com.tygq.demo04.service.IGeneralDataTransactionStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/receiveMsgManager")
@Slf4j
public class GeneralDataTransactionStatusController {

    @Autowired
    private IGeneralDataTransactionStatusService generalDataTransactionStatusService;

    /**
     * 已发送的工单列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "20") Integer size,
                             Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<GeneralDataTransactionStatus> generalDataTransactionStatusPage = generalDataTransactionStatusService.findAll(request);
        map.put("generalDataTransactionStatusPage", generalDataTransactionStatusPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("kafkaManager/receiveMsgList", map);
    }

    @GetMapping("/getCorrelateData")
    public ModelAndView getCorrelateData(String correlateid,Map<String, Object> map){
        GeneralDataTransactionStatus generalDataTransactionStatus = null;
        try {
            generalDataTransactionStatus = generalDataTransactionStatusService.findByCorrelateid(correlateid);
        }catch (Exception e){
            log.error("【查询回执工单】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("generalDataTransactionStatus", generalDataTransactionStatus);
        return new ModelAndView("kafkaManager/generalDataTransactionStatusDetail", map);
    }

}
