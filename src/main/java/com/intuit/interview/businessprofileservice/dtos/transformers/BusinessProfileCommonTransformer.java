package com.intuit.interview.businessprofileservice.dtos.transformers;

import com.intuit.interview.businessprofileservice.annotations.Transformer;
import com.intuit.interview.businessprofileservice.dtos.request.common.AddressDto;
import com.intuit.interview.businessprofileservice.dtos.request.common.TaxIdentifiersDto;
import com.intuit.interview.businessprofileservice.dtos.response.common.AddressResponse;
import com.intuit.interview.businessprofileservice.dtos.response.common.TaxIdentifiersResponse;
import com.intuit.interview.businessprofileservice.models.common.Address;
import com.intuit.interview.businessprofileservice.models.common.TaxIdentifiers;

@Transformer(value = "BusinessProfileCommonTransformer")
public class BusinessProfileCommonTransformer {
    public static Address convertAddressDtoToAddress(AddressDto addressDto) {
        return Address.builder()
                .line1(addressDto.getLine1())
                .line2(addressDto.getLine2())
                .city(addressDto.getCity())
                .zip(addressDto.getZip())
                .state(addressDto.getState())
                .country(addressDto.getCountry())
                .build();
    }

    public static TaxIdentifiers convertTaxIdentifiersDtoToTaxIdentifiers(TaxIdentifiersDto taxIdentifiersDto) {
        return TaxIdentifiers.builder()
                .pan(taxIdentifiersDto.getPan())
                .ein(taxIdentifiersDto.getEin())
                .build();
    }

    public static AddressResponse convertAddressToAddressResponse(Address address) {
        return AddressResponse.builder()
                .line1(address.getLine1())
                .line2(address.getLine2())
                .city(address.getCity())
                .zip(address.getZip())
                .state(address.getState())
                .country(address.getCountry())
                .build();
    }

    public static TaxIdentifiersResponse convertTaxIdentifiersToTaxIdentifiersResponse(TaxIdentifiers taxIdentifiers) {
        return TaxIdentifiersResponse.builder()
                .pan(taxIdentifiers.getPan())
                .ein(taxIdentifiers.getEin())
                .build();
    }
}
