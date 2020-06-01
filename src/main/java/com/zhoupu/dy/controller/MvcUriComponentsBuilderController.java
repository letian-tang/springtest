package com.zhoupu.dy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

/**
 * 代码描述
 * <p>
 *
 * @author 阿汤
 * @since 2019/11/22 11:35
 */
@RestController
public class MvcUriComponentsBuilderController {

    @GetMapping("/test1")
    public String test1() {
        return "你好吗test1";
    }

    @GetMapping("/test2")
    public String mystest() {
        return "你好吗test2";
    }

    @GetMapping("/test3")
    public String test3() {
        MvcUriComponentsBuilder.fromController(MvcUriComponentsBuilderController.class).build();
        return MvcUriComponentsBuilder.fromMappingName("MUCBC#mystest").build();
    }
}
