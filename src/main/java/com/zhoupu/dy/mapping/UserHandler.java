package com.zhoupu.dy.mapping;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
public class UserHandler {

    public @ResponseBody String getUser(Long id){
        return "糖糖";
    }
}
