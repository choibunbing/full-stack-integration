package main.java.rocketmq.shedule;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

public class ScheduledMessageConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("default");
        consumer.subscribe("TestTopic", "*");
        consumer.registerMessageListener((MessageListenerConcurrently) (messages, context) -> {
            for (MessageExt message : messages) {
                System.out.println("Receive message[msgId=" + message.getMsgId() + "]" + (System.currentTimeMillis() - message.getStoreTimestamp()) + "ms later");
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
    }

}
