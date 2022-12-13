package com.hyena.springproxy.b_jdkdynamicproxy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

import static org.assertj.core.api.Assertions.assertThat;

class JdkDynamicProxyTest {

    @Test
    @DisplayName("기존 Sample의 메서드 getLowerCaseA(), getLowerCaseB(), getLowerCaseC()는 소문자 반환에 성공한다.")
    void sample_original_test() {
        // given
        com.hyena.springproxy.a_proxypattern.Sample sample = new com.hyena.springproxy.a_proxypattern.SampleTarget();

        // when
        String a = sample.getLowerCaseA();
        String b = sample.getLowerCaseB();
        String c = sample.getLowerCaseC();

        // then
        assertThat(a).isEqualTo("a");
        assertThat(b).isEqualTo("b");
        assertThat(c).isEqualTo("c");
    }

    /**
     * ✅ JDK에서 지원하는 프록시
     *
     * - Reflection API 이용
     * - 인터페이스가 있어야 한다.
     * - InvocationHandler 를 오버라이딩 해야한다.
     */
    @Test
    @DisplayName("메서드 이름이 getLowerCase로 시작하면 jdk 프록시가 동작하며 소문자 반환이 아닌 대문자 반환에 성공한다.")
    void jdk_dynamic_proxy_test() {
        // given
        Sample sample = (Sample) Proxy.newProxyInstance(
                JdkDynamicProxyTest.class.getClassLoader(),
                new Class[] {Sample.class},
                new UpperCaseInvocationHandler(new SampleTarget())
        );

        // when
        String a = sample.getLowerCaseA();
        String b = sample.getLowerCaseB();
        String c = sample.getLowerCaseC();

        // then
        assertThat(a).isEqualTo("A");
        assertThat(b).isEqualTo("B");
        assertThat(c).isEqualTo("C");
    }
}