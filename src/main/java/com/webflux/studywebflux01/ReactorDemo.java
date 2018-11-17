package com.webflux.studywebflux01;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Wang Chen Peng
 * @date 2018/11/17
 * describe: Reactory的一个demo
 */
public class ReactorDemo {

    public static void main(String[] args) {
        // reactor = jdk8 stream + jdk9 reactive stream
        // Mono 0-1个元素
        // Flux 0-N个元素
        ArrayList<Integer> collect = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toCollection(ArrayList::new));

        //定义订阅者
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {

            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                //保存订阅关系,需要用它来给发布者响应
                this.subscription=subscription;
                //请求一个数据
                this.subscription.request(1);
            }

            @Override
            public void onNext(Integer integer) {
                //接受一个数据 处理
                System.out.println("接受到一个数据: " + integer);

                //此处模拟执行业务逻辑的耗时
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //处理完数据调用request再请求一个数据
                this.subscription.request(1);
                //如果已经达到了目标 则可以调用cancel告诉发布者不再接受数据了
                //this.subscription.cancel

            }

            @Override
            public void onError(Throwable throwable) {
                //出现了异常 打印错误信息
                throwable.printStackTrace();
                //告诉发布者 不能再接受数据了 因为出错了
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                //全部数据处理完了(或者发布者关闭了发布)
                System.out.println("处理完毕");
            }
        };

        Flux.fromStream(collect.stream()).subscribe(subscriber);

    }
}
