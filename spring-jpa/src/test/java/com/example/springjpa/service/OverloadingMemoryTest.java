package com.example.garbagecollector.service;

import org.junit.jupiter.api.Test;


class OverloadingMemoryTest {

    private OverloadingMemory overloadingMemory = new OverloadingMemory();

    @Test
    void generateNumberOfObject() throws InterruptedException {
        Thread.sleep(10_000);

        for (int i = 0; i < 50; i++) {
            overloadingMemory.generateNumberOfObject(1_000_000);
            Thread.sleep(1_000);
        }

        System.gc();
        Thread.sleep(20_000);

    }

    @Test
    void generateNumberOfObjectWithMemoryLeak() throws InterruptedException {

        Thread.sleep(10_000);

        for (int i = 0; i < 50; i++) {
            overloadingMemory.generateNumberOfObjectWithMemoryLeak(1_000_000);
            Thread.sleep(1_000);
        }

        Thread.sleep(10_000);

    }

}