package com.doruk.customer;

import com.doruk.customer.controller.CustomerService;
import com.doruk.customer.domain.AddressService;
import com.doruk.customer.domain.CustomerServiceImpl;
import com.doruk.customer.infrastructure.AddressServiceGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Configuration
@EnableBinding(Sink.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Autowired
    private RedisConfiguration redisConfiguration;

    @Bean
    CustomerService customerService(AddressService addressService){
        return new CustomerServiceImpl(addressService);
    }

    @Bean
    AddressService addressService(RestTemplate restTemplate, AddressServiceConfiguration configuration){
        return new AddressServiceGateway(restTemplate,configuration);
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName("localhost");
        jedisConnectionFactory.setPort(6379);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}