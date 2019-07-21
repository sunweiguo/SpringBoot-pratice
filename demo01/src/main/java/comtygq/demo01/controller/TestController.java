package comtygq.demo01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("index")
    public String index(){
        return "hello world";
    }

    @GetMapping(value = "/hello/{name}")
    public String hello(@PathVariable("name") String name){
        return "hello ~~ "+ name;
    }
}
