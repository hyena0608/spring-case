package com.hyena.springproxy.b_jdkdynamicproxy;

public class SampleTarget implements Sample {

    public String getLowerCaseA() {
        return "a";
    }

    public String getLowerCaseB() {
        return "b";
    }

    public String getLowerCaseC() {
        return "c";
    }
}
