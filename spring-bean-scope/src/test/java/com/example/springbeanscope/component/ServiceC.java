package com.example.springbeanscope.component;

public class ServiceC {
    private final ServiceA serviceA;

    public ServiceC(final ServiceA serviceA) {
        this.serviceA = serviceA;
    }

    public ServiceA getServiceA() {
        return serviceA;
    }
}
