package com.doruk.address.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddressResponse {

    private String country;
    private String town;
    private String zipCode;

}
