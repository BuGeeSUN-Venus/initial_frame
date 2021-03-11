package com.example.initial_frame.common.mq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;

public class TopicDemo {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("HAOKE_IM");

        defaultMQProducer.setNamesrvAddr("127.0.0.1:9876");

        defaultMQProducer.start();

        /**
         * key：broker名称 ,newTopic：topic名称, queueNum：队列数（分区）
         */
        defaultMQProducer.createTopic("broker_haoke_im", "my_topic", 8);

        System.out.println("创建topic成功");

        defaultMQProducer.shutdown();
    }
}

