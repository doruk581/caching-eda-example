package com.doruk.address;

import com.doruk.address.controller.AddressService;
import com.doruk.address.domain.AddressServiceImpl;
import com.doruk.address.infrastructure.AddressRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableBinding(Source.class)
public class AddressServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddressServiceApplication.class, args);
    }

    @Bean
    AddressService addressService(AddressRepository addressRepository){
        return new AddressServiceImpl(addressRepository);
    }
}
