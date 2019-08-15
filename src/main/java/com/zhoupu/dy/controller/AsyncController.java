package com.zhoupu.dy.controller;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import lombok.extern.slf4j.Slf4j;

/**
 * SpringMVC的异步请求
 */
@Controller
@RestController
@Slf4j
public class AsyncController {

    @Autowired
    private ThreadPoolTaskExecutor asyncTaskExecutor;

    @GetMapping("/callable")
    public Callable<String> callable() {
        log.info("threadName={}", Thread.currentThread().getName());
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5 * 1000);
                log.info("threadName={}", Thread.currentThread().getName());
                log.info("Callable子线程执行ing...");
                return "Callable:" + "久等了";
            }
        };
    }

    @GetMapping(value = "/webasynctask")
    public WebAsyncTask<String> webAsyncTask() {
        log.info("threadName={}", Thread.currentThread().getName());
        return new WebAsyncTask<String>(7 * 1000, () -> {
            Thread.sleep(5 * 1000);
            log.info("threadName={}", Thread.currentThread().getName());
            log.info("WebAsyncTask 子线程执行ing...");
            return "WebAsyncTask:" + "久等了";
        });
    }

    @GetMapping(value = "/deferred")
    public DeferredResult<String> deferred() throws Exception {
        log.info("threadName={}", Thread.currentThread().getName());
        DeferredResult<String> result = new DeferredResult<String>(7 * 1000L, "超时了");
        approve(result);
        TimeUnit.SECONDS.sleep(7);
        return result;
    }

    private void approve(final DeferredResult<String> result) {
        new Thread(() -> {
            try {
                log.info("threadName={}", Thread.currentThread().getName());
                Thread.sleep(5 * 1000);
                result.setResult("同意:" + LocalDateTime.now());
            } catch (InterruptedException e) {
            }
        }).start();
    }

    @GetMapping(value = "/future")
    public ListenableFuture<String> future() {
        ListenableFuture<String> listenableFuture = asyncTaskExecutor.submitListenable(() -> {
            Thread.sleep(5000); // 模拟耗时操作
            return "success";
        });
        listenableFuture.addCallback(new ListenableFutureCallback<String>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("调用失败");
            }

            @Override
            public void onSuccess(String s) {
                System.out.println("调用成功：" + s);
            }
        });
        return listenableFuture;
    }
}
