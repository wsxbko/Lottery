package cn.itedus.lottery;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
@SpringBootApplication
@Configurable
@EnableDubbo
public class LotteryApplication {

    public static void main(String[] args) {

        SpringApplication.run(LotteryApplication.class, args);
//        ApplicationContext ctx = SpringApplication.run(LotteryApplication.class, args);
//        String[] beanNames = ctx.getBeanNamesForAnnotation(Service.class);
//
//        System.out.println("Service注解beanNames个数：" + beanNames.length);
//
//        for (String bn: beanNames) {
//            System.out.println(bn);
//        }
    }
}
