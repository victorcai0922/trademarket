package com.start.demo.domain;

import com.start.demo.service.IHttpService;
import com.start.demo.utils.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class MessageSender implements RabbitTemplate.ConfirmCallback {

    private  RabbitTemplate rabbitTemplate;

    @Autowired
    private IHttpService httpService;

    @Autowired
    public MessageSender(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
    }

    public void sendMsg(String content){
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        try {
            content = httpService.doGet("https://api.wmcloud.com/data/v1//api/equity/getMktEqudCCXE.json?field=&secID=000001.XSHE&startDate=&endDate=");

        }catch (Exception ex){

        }

        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE,RabbitConfig.ROUTINGKEY,content,correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("回调id: "+correlationData);
        if (ack){
            System.out.println("消息成功消费");
        }else
        {
            System.out.println("消息消费失败:"+cause);
        }
    }
}
