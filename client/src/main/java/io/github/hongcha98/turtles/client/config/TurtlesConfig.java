package io.github.hongcha98.turtles.client.config;

public class TurtlesConfig {

    private String brokerHost = "127.0.0.1";

    private int brokerPort = 9999;

    private String username = "turtles";

    private String password = "turtles";

    private String groupName;

    private int sendThreadNum = 32;

    // 没有消息时下一次拉取消息的间隔
    private int pullMessageInterval = 300;

    public int getPullMessageInterval() {
        return pullMessageInterval;
    }

    public void setPullMessageInterval(int pullMessageInterval) {
        this.pullMessageInterval = pullMessageInterval;
    }

    public int getSendThreadNum() {
        return sendThreadNum;
    }

    public void setSendThreadNum(int sendThreadNum) {
        this.sendThreadNum = sendThreadNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getBrokerHost() {
        return brokerHost;
    }

    public void setBrokerHost(String brokerHost) {
        this.brokerHost = brokerHost;
    }

    public int getBrokerPort() {
        return brokerPort;
    }

    public void setBrokerPort(int brokerPort) {
        this.brokerPort = brokerPort;
    }
}
