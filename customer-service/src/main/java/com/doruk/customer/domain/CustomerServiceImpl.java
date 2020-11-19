package com.doruk.customer.domain;

import com.doruk.customer.controller.CustomerService;
import com.doruk.customer.infrastructure.AddressDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final AddressService addressService;

    @Autowired
    private RedisRepository redisRepository;

    public CustomerServiceImpl(AddressService addressService) {
        this.addressService = addressService;
    }

    private Address checkRedisCache(String id) {
        try {
            return redisRepository.find(id);
        }
        catch (Exception ex){
            return null;
        }
    }

    @Override
    public void process(String id) {

         Address address = checkRedisCache(id);

        if (address == null){
            final AddressDto addressDto = addressService.getAddressInformation(id);
             address =
                Address.builder()
                    .country(addressDto.getCountry())
                    .zipCode(addressDto.getZipCode()).town(addressDto.getTown())
                    .id(id).build();

             if (address != null)
                 cacheAddressObject(address);
        }

        final CustomerInformation customerInformation =
            CustomerInformation.builder().address(address).identityInformation(id).build();

        log.info("Customer information: " + customerInformation.toString());
    }

    private void cacheAddressObject(Address address) {
        try {
            redisRepository.save(address);
        }catch (Exception ex){
            log.error("Error on caching address object!");
        }
    }
}