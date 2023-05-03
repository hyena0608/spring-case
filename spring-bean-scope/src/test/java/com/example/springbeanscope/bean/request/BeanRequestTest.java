package com.example.springbeanscope.bean.request;

import com.example.springbeanscope.domain.Member;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@ComponentScan(basePackageClasses = BeanRequestTest.TestConfig.class)
public class BeanRequestTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Scopes a single bean definition to the lifecycle of a single HTTP request.
     * That is, each HTTP request has its own instance of a bean created off the back of a single bean definition.
     * Only valid in the context of a web-aware Spring ApplicationContext.
     */
    @TestConfiguration
    static class TestConfig {
        @Bean
        @Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
        public Member member() {
            return new Member("hyena");
        }
    }

    @Test
    void 요청_빈() {
        final ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        final Member member = ac.getBean(Member.class);
        final Member member2 = ac.getBean(Member.class);

        log.info("member={}", member);
        log.info("member2={}", member2);

        assertThat(member).isNotEqualTo(member2);
    }
}
