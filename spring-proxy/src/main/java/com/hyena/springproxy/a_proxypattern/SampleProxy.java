package com.hyena.springproxy.a_proxypattern;

public class SampleProxy implements Sample {

    private Sample sample;

    public SampleProxy(Sample sample) {
        this.sample = sample;
    }

    @Override
    public String getLowerCaseA() {
        return sample.getLowerCaseA().toUpperCase();
    }

    @Override
    public String getLowerCaseB() {
        return sample.getLowerCaseB().toUpperCase();
    }

    @Override
    public String getLowerCaseC() {
        return sample.getLowerCaseC().toUpperCase();
    }
}
