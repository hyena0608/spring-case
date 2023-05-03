package com.example.springbeanscope.configuration;

public class ServiceB {
    private final ServiceA serviceA;

    public ServiceB(final ServiceA serviceA) {
        this.serviceA = serviceA;
    }

    public ServiceA getServiceA() {
        return serviceA;
    }
}
