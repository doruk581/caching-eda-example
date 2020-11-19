package com.doruk.customer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping(value = "/v1/customer/process/{id}")
    public ResponseEntity<Object> processSomeBusiness(@PathVariable String id){
        customerService.process(id);

        return ResponseEntity.noContent().build();
    }
}
