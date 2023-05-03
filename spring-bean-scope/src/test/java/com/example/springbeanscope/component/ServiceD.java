package com.example.springbeanscope.component;

public class ServiceD {
    private final ServiceA serviceA;

    public ServiceD(final ServiceA serviceA) {
        this.serviceA = serviceA;
    }

    public ServiceA getServiceA() {
        return serviceA;
    }
}
