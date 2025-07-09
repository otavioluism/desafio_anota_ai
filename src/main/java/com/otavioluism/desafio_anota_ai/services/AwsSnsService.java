package com.otavioluism.desafio_anota_ai.services;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.otavioluism.desafio_anota_ai.DTOs.MessageTopicDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AwsSnsService {

    @Autowired
    private AmazonSNS snsClient;

    @Autowired
    @Qualifier("catalogEventsTopic")
    private Topic catalogTopic;

    public void publish(MessageTopicDTO message) {
        this.snsClient.publish(catalogTopic.getTopicArn(), message.message());
    }

}
