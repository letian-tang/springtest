package com.zhoupu.dy.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/t")
@Slf4j
public class ResponseBodyEmitterController {

    @Autowired
    private ThreadPoolTaskExecutor asyncTaskExecutor;

    /**
     * 如果不设置为0，那么如果SseEmitter在指定的时间（AsyncSupportConfigurer设置的timeout,默认为30秒)未完成会抛出异常
     * 
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/t1")
    public SseEmitter sseDemo() throws InterruptedException {
        final SseEmitter emitter = new SseEmitter(0L); // timeout设置为0表示不超时
        asyncTaskExecutor.execute(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    emitter.send("hello" + i);
                    log.info("emit:{}", "hello" + i);
                    Thread.sleep(1000 * 1);
                }
                emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }

    /**
     * 
     * @return
     */
    @GetMapping("/t2")
    public ResponseBodyEmitter handle() {
        final ResponseBodyEmitter responseBodyEmitter = new ResponseBodyEmitter();
        asyncTaskExecutor.execute(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    responseBodyEmitter.send("hello" + i, MediaType.TEXT_PLAIN);
                    log.info("emit:{}", "hello" + i);
                    Thread.sleep(1000 * 1);
                }
                responseBodyEmitter.complete();
            } catch (Exception e) {
                responseBodyEmitter.completeWithError(e);
            }
        });
        return responseBodyEmitter;
    }

    @Autowired
    private ApplicationContext context;

    /**
     * 
     * @return
     */
    @GetMapping("/download")
    public ResponseEntity<StreamingResponseBody> handleDownload() {

        StreamingResponseBody streamingResponseBody = (outputStream) -> {
            Resource resource = context.getResource("classpath:temp/template.xlsx");
            IOUtils.copy(resource.getInputStream(), outputStream);
            outputStream.flush();
            outputStream.close();
        };
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=template.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM).body(streamingResponseBody);
    }

}
