package com.doruk.customer.infrastructure.redis;

import com.doruk.customer.domain.Address;
import com.doruk.customer.domain.RedisRepository;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultRedisRepository implements RedisRepository {

    private static final String NAME = "address";
    private RedisTemplate<String,Address> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public DefaultRedisRepository(
        RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Address address) {
        redisTemplate.opsForHash().put(NAME,address.getId(),address);
    }


    @Override
    public void delete(String id) {
        hashOperations.delete(NAME,id);
    }

    @Override
    public Address find(String id) {
        return (Address) hashOperations.get(NAME,id);
    }
}
