package com.example.springbeanscope.configuration;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackageClasses = ConfigurationTest.MyConfig.class)
class ConfigurationTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Configuration
    static class MyConfig {
        private final Logger log = LoggerFactory.getLogger(this.getClass());

        @Bean
        public ServiceA serviceA() {
            return new ServiceA();
        }

        @Bean
        public ServiceB serviceB() {
            final ServiceA serviceA = serviceA();
            log.info("B에 넣은 A = {}", serviceA);
            return new ServiceB(serviceA);
        }

        @Bean
        public ServiceC serviceC() {
            final ServiceA serviceA = serviceA();
            log.info("C에 넣은 A = {}", serviceA);
            return new ServiceC(serviceA);
        }

        @Bean
        public ServiceD serviceD() {
            final ServiceA serviceA = serviceA();
            log.info("D에 넣은 A = {}", serviceA);
            return new ServiceD(serviceA);
        }
    }

    @Test
    void Configuration으로_등록() {
        final var ac = new AnnotationConfigApplicationContext(MyConfig.class);

        final ServiceA serviceA = ac.getBean(ServiceA.class);
        final ServiceB serviceB = ac.getBean(ServiceB.class);
        final ServiceC serviceC = ac.getBean(ServiceC.class);
        final ServiceD serviceD = ac.getBean(ServiceD.class);

        log.info("A = {}", serviceA.getClass());
        log.info("B = {}", serviceB.getServiceA());
        log.info("C = {}", serviceC.getServiceA());
        log.info("D = {}", serviceD.getServiceA());
    }
}
