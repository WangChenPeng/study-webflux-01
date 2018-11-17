package com.webflux.studywebflux01.controller;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Wang Chen Peng
 * @date 2018/11/17
 * describe: 一个用来测试同步和异步区别的controller
 */
@RestController
@Log4j2
public class TestSyncOrAsyncController {

    @GetMapping("/sync")
    private String sync(){
        log.info("hit syncController " );
        String str = createStr();
        log.info("return syncController" );
        return str;
    }

    @GetMapping("/async")
    private Mono<String> async(){
        log.info("hit asyncController " );
        Mono<String> stringMono = Mono.fromSupplier(() -> createStr());
        log.info("return asyncController" );
        return stringMono;
    }

    @GetMapping(value = "/flux01/{start}/{end}",produces = "text/event-stream")
    private Flux<String> flux01(@PathVariable int start,@PathVariable int end){
        log.info("hit asyncController " );
        Flux<String> stringFlux = Flux.fromStream(IntStream.rangeClosed(start, end).mapToObj((x) -> {
            try {
                TimeUnit.MILLISECONDS.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "流式处理第 :" + x +" 条数据";
        }));
        log.info("return asyncController" );
        return stringFlux;
    }

    /**
     * 总结:
     *          webFlux和传统tomcat的不同点在于
     *          当一个连接进入时 timcat是连接阻塞 一直等到返回了数据才恢复
     *          而 webFlux 时 利用惰性求值 的原理 线程不阻塞 异步的刷出 同样的线程可以处理更多的链接 实现高并发
     */


    private String createStr(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "this is test string";
    }
}
