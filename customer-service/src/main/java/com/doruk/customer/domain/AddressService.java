package com.doruk.customer.domain;

import com.doruk.customer.infrastructure.AddressDto;

public interface AddressService {

    AddressDto getAddressInformation(final String id);
}