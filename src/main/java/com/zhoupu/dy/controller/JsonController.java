package com.zhoupu.dy.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 代码描述<p>
 *
 * @author 阿汤
 * @since 2019/12/7 13:51
 */
@RestController
public class JsonController {

    @PostMapping("/test2")
    public String test(){
        if(true){
            throw new RuntimeException();
        }
        return "";
    }
}
