package com.doruk.address.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddAddressRequest {
    private String id;
    private String country;
    private String town;
    private String zipCode;
}
