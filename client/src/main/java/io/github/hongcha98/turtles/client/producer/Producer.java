package io.github.hongcha98.turtles.client.producer;

import io.github.hongcha98.turtles.client.ClientApi;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface Producer extends ClientApi {
    /**
     * 返回消息id
     *
     * @param topic
     * @param msg
     * @return
     */
    default String send(String topic, Object msg) {
        return send(topic, Collections.emptyMap(), msg);
    }

    /**
     * 返回消息id
     *
     * @param topic
     * @param msg
     * @return
     */
    String send(String topic, Map<String, String> header, Object msg);

    /**
     * 异步发送
     */
    default CompletableFuture<String> asyncSend(String topic, Object msg) {
        return asyncSend(topic, Collections.emptyMap(), msg);
    }

    /**
     * 异步发送
     */
    CompletableFuture<String> asyncSend(String topic, Map<String, String> header, Object msg);
}
