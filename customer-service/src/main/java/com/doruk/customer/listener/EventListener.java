package com.doruk.customer.listener;

import com.doruk.customer.domain.RedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventListener {

    @Autowired
    private RedisRepository redisRepository;

    @StreamListener(Sink.INPUT)
    public void processAddressData(ChangedModel model) {
        switch (model.getAction()) {
            case "UPDATE":
                redisRepository.delete(model.getId());
                break;
            case "DELETE":
                redisRepository.delete(model.getId());
                break;
            default:
                log.info("Unidentified action!");
                break;
        }
    }
}
