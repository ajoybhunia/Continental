package org.tw.continental.service;

import org.springframework.stereotype.Component;

@Component
public class MyIdGenerator {
    private int id;

    public MyIdGenerator() {
        this.id = 0;
    }

    public int next() {
        return id++;
    }
}
