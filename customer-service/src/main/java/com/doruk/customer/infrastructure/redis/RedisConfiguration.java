package com.doruk.customer.infrastructure.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("redis")
@Getter
@Setter
public class RedisConfiguration {
    @Value("host")
    public String hostName;
}
