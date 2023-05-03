package com.example.springbeanscope.bean.prototype;

import com.example.springbeanscope.domain.Member;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

@ComponentScan(basePackageClasses = BeanPrototypeTest.TestConfig.class)
class BeanPrototypeTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @TestConfiguration
    static class TestConfig {
        @Bean
        @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public Member member() {
            return new Member("hyena");
        }
    }

    @Test
    void 프로토타입_빈() {
        final ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        final Member member = ac.getBean(Member.class);
        final Member member2 = ac.getBean(Member.class);

        log.info("member={}", member);
        log.info("member2={}", member2);

        assertThat(member).isNotEqualTo(member2);
    }
}
