package com.hongcha.hcmq.broker;

public interface LifeCycle {

    default void start() {
        // 啥都不做
    }

    default void close() {
        // 啥都不做
    }
}
