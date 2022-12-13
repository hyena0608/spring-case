package com.hyena.springproxy.b_jdkdynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ✅ reflection 을 이용하는 JdkDynamicProxy
 *
 * - 프록시 클래스를 직접 구현하지 않아 코드의 복잡도를 해소한다.
 * - InvocationHandler 를 이용해서 중복 코드를 제거한다.
 */
public class UpperCaseInvocationHandler implements InvocationHandler {

    private final Object target;

    public UpperCaseInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("getLowerCase")) {
            return ((String) method.invoke(target, args)).toUpperCase();
        }
        return method.invoke(target, args);
    }
}
