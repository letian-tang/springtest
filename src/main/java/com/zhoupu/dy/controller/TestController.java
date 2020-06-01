package com.zhoupu.dy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 代码描述<p>
 *
 * @author 阿汤
 * @since 2019/11/17 15:57
 */
@Controller
public class TestController {

    @PostMapping("/ss")
    public String getS(@RequestParam Map<String,Object> params){
        return "";
    }
}
