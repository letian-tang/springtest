package com.zhoupu.dy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zhoupu.dy.mapping.ApiVersion;

/**
 * 测试RequestMappingHandlerMapping和ApiVersionRequestCondition
 */
@Controller
@RequestMapping("/api/{version}")
public class TestController {

    @GetMapping("/user/{id}")
    @ApiVersion(2)
    public @ResponseBody String getUser(@PathVariable("id") String id) {
        return "中午";
    }

    @GetMapping("/user/{id}")
    @ApiVersion(4)
    public @ResponseBody String getUserV4(@PathVariable("id") String id) {
        return "下午";
    }
}
