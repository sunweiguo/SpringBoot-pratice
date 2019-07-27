package com.tygq.demo03.controller;

import com.tygq.demo03.entity.GeneralDataTransaction;
import com.tygq.demo03.service.IGeneralDataTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/sendMsgManager")
public class GeneralDataTransactionController {

    @Autowired
    private IGeneralDataTransactionService generalDataTransactionService;

    /**
     * index首页
     * @return
     */
    @GetMapping("index")
    public ModelAndView index(){
        return new ModelAndView("kafkaManager/index");
    }

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
        Page<GeneralDataTransaction> generalDataTransactionPage = generalDataTransactionService.findAll(request);
        map.put("generalDataTransactionPage", generalDataTransactionPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("kafkaManager/sendMsgList", map);
    }

    /**
     * 发送工单的页面
     * @return
     */
    @GetMapping("sendMsgPage")
    public ModelAndView sendMsgPage(){
        return new ModelAndView("kafkaManager/sendMsg");
    }




}
