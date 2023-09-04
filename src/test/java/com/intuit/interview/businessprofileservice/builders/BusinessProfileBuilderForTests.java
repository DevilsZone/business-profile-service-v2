package com.intuit.interview.businessprofileservice.builders;

import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileCreateDto;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileUpdateDto;
import com.intuit.interview.businessprofileservice.dtos.request.common.AddressDto;
import com.intuit.interview.businessprofileservice.dtos.request.common.TaxIdentifiersDto;
import com.intuit.interview.businessprofileservice.dtos.response.BusinessProfileResponse;
import com.intuit.interview.businessprofileservice.dtos.response.common.AddressResponse;
import com.intuit.interview.businessprofileservice.dtos.response.common.TaxIdentifiersResponse;
import com.intuit.interview.businessprofileservice.models.BusinessProfile;
import com.intuit.interview.businessprofileservice.models.common.Address;
import com.intuit.interview.businessprofileservice.models.common.TaxIdentifiers;

public class BusinessProfileBuilderForTests {

    public static BusinessProfile createBusinessProfile() {
        BusinessProfile businessProfile = new BusinessProfile();
        businessProfile.setCompanyName("Old Company");
        businessProfile.setLegalName("Old Legal");
        businessProfile.setEmail("old@example.com");
        businessProfile.setWebsite("https://old.com");
        businessProfile.setBusinessAddress(createAddress());
        businessProfile.setLegalAddress(createAddress());
        businessProfile.setTaxIdentifiers(createTaxIdentifiers());
        businessProfile.setVersion(1L);
        businessProfile.setCreatedAt(0L);
        businessProfile.setCreatedBy("Test");
        businessProfile.setUpdatedAt(0L);
        businessProfile.setUpdatedBy("Test");
        return businessProfile;
    }

    public static Address createAddress() {
        Address address = new Address();
        address.setLine1("123");
        address.setLine2("123");
        address.setCity("123");
        address.setZip("123");
        address.setState("123");
        address.setCountry("123");
        return address;
    }

    public static TaxIdentifiers createTaxIdentifiers() {
        TaxIdentifiers taxIdentifiers = new TaxIdentifiers();
        taxIdentifiers.setPan("123");
        taxIdentifiers.setEin("123");
        return taxIdentifiers;
    }

    public static BusinessProfileCreateDto createBusinessProfileDto() {
        BusinessProfileCreateDto businessProfileCreateDto = new BusinessProfileCreateDto();
        businessProfileCreateDto.setCompanyName("Test Company");
        businessProfileCreateDto.setLegalName("Test Legal");
        businessProfileCreateDto.setEmail("test@example.com");
        businessProfileCreateDto.setWebsite("https://test.com");
        businessProfileCreateDto.setBusinessAddress(createAddressDto());
        businessProfileCreateDto.setLegalAddress(createAddressDto());
        businessProfileCreateDto.setTaxIdentifiers(createTaxIdentifiersDto());
        businessProfileCreateDto.setCreatedBy("Test-123");
        return businessProfileCreateDto;
    }

    public static AddressDto createAddressDto() {
        AddressDto addressDto = new AddressDto();
        addressDto.setLine1("123");
        addressDto.setLine2("123");
        addressDto.setCity("123");
        addressDto.setZip("123");
        addressDto.setState("123");
        addressDto.setCountry("123");
        return addressDto;
    }

    public static TaxIdentifiersDto createTaxIdentifiersDto() {
        TaxIdentifiersDto taxIdentifiersDto = new TaxIdentifiersDto();
        taxIdentifiersDto.setPan("123");
        taxIdentifiersDto.setEin("123");
        return taxIdentifiersDto;
    }

    public static BusinessProfileUpdateDto createBusinessProfileUpdateDto() {
        BusinessProfileUpdateDto businessProfileUpdateDto = new BusinessProfileUpdateDto();
        businessProfileUpdateDto.setCompanyName("Updated Company");
        businessProfileUpdateDto.setLegalName("Updated Legal");
        businessProfileUpdateDto.setEmail("updated@example.com");
        businessProfileUpdateDto.setWebsite("https://updated.com");
        businessProfileUpdateDto.setBusinessAddress(createAddressDto());
        businessProfileUpdateDto.setLegalAddress(createAddressDto());
        businessProfileUpdateDto.setTaxIdentifiers(createTaxIdentifiersDto());
        businessProfileUpdateDto.setUpdatedBy("Test");
        return businessProfileUpdateDto;
    }

    public static BusinessProfileResponse createBusinessProfileResponse() {
        BusinessProfileResponse businessProfileUpdateDto = BusinessProfileResponse.builder().build();
        businessProfileUpdateDto.setCompanyName("Updated Company");
        businessProfileUpdateDto.setLegalName("Updated Legal");
        businessProfileUpdateDto.setEmail("updated@example.com");
        businessProfileUpdateDto.setWebsite("https://updated.com");
        businessProfileUpdateDto.setBusinessAddress(createAddressResponse());
        businessProfileUpdateDto.setLegalAddress(createAddressResponse());
        businessProfileUpdateDto.setTaxIdentifiers(createTaxIdentifiersResponse());
        return businessProfileUpdateDto;
    }

    public static AddressResponse createAddressResponse() {
        AddressResponse addressResponse = AddressResponse.builder().build();
        addressResponse.setLine1("123");
        addressResponse.setLine2("123");
        addressResponse.setCity("123");
        addressResponse.setZip("123");
        addressResponse.setState("123");
        addressResponse.setCountry("123");
        return addressResponse;
    }

    public static TaxIdentifiersResponse createTaxIdentifiersResponse() {
        TaxIdentifiersResponse taxIdentifiersResponse = TaxIdentifiersResponse.builder().build();
        taxIdentifiersResponse.setPan("123");
        taxIdentifiersResponse.setEin("123");
        return taxIdentifiersResponse;
    }
}
