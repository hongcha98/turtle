package io.github.hongcha98.turtles.client.tset.producer;

import io.github.hongcha98.turtles.client.config.TurtlesConfig;
import io.github.hongcha98.turtles.client.producer.DefaultProducer;
import io.github.hongcha98.turtles.client.producer.Producer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ProducerTest {
    private static final String TOPIC = "test-topic";

    public static void main(String[] args) throws Exception {
        TurtlesConfig turtlesConfig = new TurtlesConfig();
        turtlesConfig.setGroup("producer-test");
        Producer producer = new DefaultProducer(turtlesConfig);
        producer.start();
        producer.deleteTopic(TOPIC);
//        producer.createTopic(TOPIC, 8);
//        asyncSend(producer, 100);
        producer.close();
    }

    public static void send(Producer producer, int number) {
        long l = System.currentTimeMillis();
        for (int i = 0; i < number; i++) {
            producer.send(TOPIC, "hello world" + i);
        }
        System.out.println("time cost :" + (System.currentTimeMillis() - l));
    }

    public static void asyncSend(Producer producer, int number) throws ExecutionException, InterruptedException {
        CompletableFuture<String>[] taskArray = new CompletableFuture[number];
        long l = System.currentTimeMillis();
        for (int i = 0; i < number; i++) {
            taskArray[i] = producer.asyncSend(TOPIC, "hello world" + i);
        }
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(taskArray);
        voidCompletableFuture.get();
        System.out.println("number : " + number + " time cost :" + (System.currentTimeMillis() - l));
    }
}