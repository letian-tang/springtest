package com.zhoupu.dy.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/t")
@Slf4j
public class ResponseBodyEmitterController {

    @GetMapping("/t2")
    public ResponseBodyEmitter handle() {
        ResponseBodyEmitter responseBodyEmitter = new ResponseBodyEmitter();
        new Thread(() -> {
            try {
                log.info("1111111");
                responseBodyEmitter.send("1111111", MediaType.TEXT_PLAIN);
                Thread.sleep(1000);

                log.info("2222222");
                responseBodyEmitter.send("2222222",MediaType.TEXT_PLAIN);
                Thread.sleep(1000);

                responseBodyEmitter.complete();
                log.info("complete");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        return responseBodyEmitter;
    }

}
