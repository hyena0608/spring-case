package com.hyena.springproxy.c_cglib;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

import static org.assertj.core.api.Assertions.assertThat;

class CglibProxyTest {

    @Test
    @DisplayName("기존 Sample의 메서드 getLowerCaseA(), getLowerCaseB(), getLowerCaseC()는 소문자 반환에 성공한다.")
    void sample_original_test() {
        // given
        Sample sample = new SampleTarget();

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
     * ✅ CGLIB 를 이용한 proxy
     *
     * - 인터페이스에도 강제로 적용 가능하다.
     *   - 클래스에도 프록시를 적용시켜야 한다.
     * - 메서드에 final 을 붙이면 오버라이딩 불가능해서 실패한다.
     * - net.sf.cglib.proxy.Enhancer 의존성 추가가 필요하다.
     * - Default 생성자가 필요하다.
     * - 타켓의 생성자를 두 번 호출한다.
     */
    @Test
    @DisplayName("메서드 이름이 getLowerCase로 시작하면 jdk 프록시가 동작하며 소문자 반환이 아닌 대문자 반환에 성공한다.")
    void jdk_dynamic_proxy_test() {
        // given
        Sample sample = (Sample) Enhancer.create(
                Sample.class,
                new UpperCaseInterceptor(new SampleTarget())
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