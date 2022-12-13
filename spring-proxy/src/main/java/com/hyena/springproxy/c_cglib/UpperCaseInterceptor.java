package com.hyena.springproxy.c_cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * ✅ CGLIB 를 이용하는 proxy
 *
 * - 바이트 코드를 조작해서 프록시 생성
 * - MethodInterceptor 를 재정의한 intercept 를 오버라이딩 해야한다.
 */
public class UpperCaseInterceptor implements MethodInterceptor {

    private final Object target;

    public UpperCaseInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if (method.getName().startsWith("getLowerCase")) {
            return ((String) method.invoke(target, args)).toUpperCase();
        }
        return method.invoke(target, args);
    }
}
