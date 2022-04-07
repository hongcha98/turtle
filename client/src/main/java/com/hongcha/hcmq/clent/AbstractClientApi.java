package com.hongcha.hcmq.clent;

import com.hongcha.hcmq.clent.config.HcmqConfig;
import com.hongcha.hcmq.common.dto.login.LoginMessageReq;
import com.hongcha.hcmq.common.dto.topic.TopicCreateMessageReq;
import com.hongcha.remote.common.spi.SpiLoader;
import com.hongcha.remote.protocol.Protocol;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractClientApi implements ClientApi {
    private final AtomicBoolean start = new AtomicBoolean(false);

    private final HcmqConfig hcmqConfig;

    private final Protocol protocol;

    private final Core core;

    public AbstractClientApi(HcmqConfig hcmqConfig) {
        this(hcmqConfig, SpiLoader.load(Protocol.class, 2));
        checkHcmqConfig(hcmqConfig);

    }

    public AbstractClientApi(HcmqConfig hcmqConfig, Protocol protocol) {
        this.hcmqConfig = hcmqConfig;
        this.protocol = protocol;
        this.core = new DefaultCore(hcmqConfig);
    }

    protected void checkHcmqConfig(HcmqConfig hcmqConfig) {
        String groupName = hcmqConfig.getGroupName();
        if (groupName == null || groupName.trim().isEmpty()) {
            throw new IllegalStateException("please configure group name");
        }
    }

    public Protocol getProtocol() {
        return protocol;
    }

    @Override
    public boolean createTopic(String topic, int queueNumber) {
        TopicCreateMessageReq topicCreateMessageReq = new TopicCreateMessageReq();
        topicCreateMessageReq.setTopicName(topic);
        topicCreateMessageReq.setQueueNumber(queueNumber);
        return core.createTopic(topicCreateMessageReq);
    }

    @Override
    public boolean deleteTopic(String topic) {
        return core.deleteTopic(topic);
    }

    @Override
    public void start() {
        if (start.compareAndSet(false, true)) {
            try {
                core.start();
                LoginMessageReq loginMessageReq = new LoginMessageReq();
                loginMessageReq.setUsername(hcmqConfig.getUsername());
                loginMessageReq.setPassword(hcmqConfig.getPassword());
                doStart();
            } catch (Exception e) {
                close();
                throw new IllegalStateException("start error", e);
            }
        }
    }

    protected abstract void doStart() throws Exception;

    @Override
    public void close() {
        if (start.compareAndSet(false, true)) {
            core.close();
            doClose();
        }
    }

    protected abstract void doClose();

    public HcmqConfig getHcmqConfig() {
        return hcmqConfig;
    }

    public Core getCore() {
        return core;
    }
}
