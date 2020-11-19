package com.doruk.address.domain;


import com.doruk.address.controller.AddressService;
import com.doruk.address.infrastructure.AddressRepository;
import com.doruk.address.models.AddAddressRequest;
import com.doruk.address.models.AddressResponse;
import com.doruk.address.models.UpdateAddressRequest;

public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public AddressResponse getAddressInformation(String id) {
        final Address address = addressRepository.findOne(id);
        return AddressResponse.builder().country(address.getCountry()).town(address.getTown()).zipCode(address.getZipCode()).build();
    }

    @Override
    public void addAddressInformation(AddAddressRequest addAddressRequest) {
        final Address address = Address.builder().country(addAddressRequest.getCountry())
            .id(addAddressRequest.getId())
            .town(addAddressRequest.getTown())
            .zipCode(addAddressRequest.getZipCode())
            .build();

        addressRepository.save(address);
    }

    @Override
    public void updateAddressInformation(UpdateAddressRequest updateAddressRequest) {
        final Address address = addressRepository.getOne(updateAddressRequest.getId());
        address.setCountry(updateAddressRequest.getCountry());
        address.setTown(updateAddressRequest.getTown());
        address.setZipCode(updateAddressRequest.getZipCode());

        addressRepository.save(address);
    }

    @Override
    public void deleteAddressInformation(String id) {
        addressRepository.delete(id);
    }
}
