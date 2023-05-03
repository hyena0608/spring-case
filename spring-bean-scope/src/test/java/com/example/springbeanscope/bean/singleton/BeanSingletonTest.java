package com.example.springbeanscope.bean.singleton;

import com.example.springbeanscope.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationPropertiesBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.util.ApplicationContextTestUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.beans.BeanProperty;

import static org.assertj.core.api.Assertions.*;

@ComponentScan(basePackageClasses = BeanSingletonTest.TestConfig.class)
public class BeanSingletonTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @TestConfiguration
    static class TestConfig {
        @Bean
        @Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
        public Member member() {
            return new Member("hyena");
        }
    }

    @Test
    void 싱글톤_빈() {
        final ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        final Member member = ac.getBean(Member.class);
        final Member member2 = ac.getBean(Member.class);

        log.info("member={}", member);
        log.info("member2={}", member2);

        assertThat(member).isEqualTo(member2);
    }
}
