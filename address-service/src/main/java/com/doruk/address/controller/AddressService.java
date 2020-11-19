package com.doruk.address.controller;


import com.doruk.address.models.AddAddressRequest;
import com.doruk.address.models.AddressResponse;
import com.doruk.address.models.UpdateAddressRequest;

public interface AddressService {
AddressResponse getAddressInformation(final String id);
void addAddressInformation(final AddAddressRequest addAddressRequest);
void updateAddressInformation(final UpdateAddressRequest updateAddressRequest);
void deleteAddressInformation(final String id);
}
