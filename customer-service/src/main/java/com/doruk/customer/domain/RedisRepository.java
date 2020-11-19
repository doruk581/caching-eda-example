package com.doruk.customer.domain;

public interface RedisRepository {

    void save(Address address);
    void delete(String id);
    Address find(String id);
}
