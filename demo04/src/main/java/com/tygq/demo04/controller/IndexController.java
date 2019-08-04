package com.tygq.demo04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @RequestMapping("index")
    public ModelAndView index(){
        return new ModelAndView("kafkaManager/index");
    }
}
