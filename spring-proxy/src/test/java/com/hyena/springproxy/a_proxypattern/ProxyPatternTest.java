package com.hyena.springproxy.a_proxypattern;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProxyPatternTest {

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
     * ✅ 프록시 패턴을 이용했을 때
     *
     * 👍 GOOD
     * - OCP 를 지킬 수 있다.
     * - SRP 를 지킬 수 있다.
     *
     * 👎 BAD
     * - 코드 복잡성이 증가한다.
     * - 중복 코드가 발생한다.
     */
    @Test
    @DisplayName("프록시 패턴을 이용하여 소문자 반환이 아닌 대문자 반환에 성공한다.")
    void sample_proxy_pattern_test() {
        // given
        Sample sample = new SampleProxy(new SampleTarget());

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