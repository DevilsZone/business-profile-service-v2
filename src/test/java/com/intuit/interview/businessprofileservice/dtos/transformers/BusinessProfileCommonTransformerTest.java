package com.intuit.interview.businessprofileservice.dtos.transformers;

import com.intuit.interview.businessprofileservice.dtos.request.common.AddressDto;
import com.intuit.interview.businessprofileservice.dtos.request.common.TaxIdentifiersDto;
import com.intuit.interview.businessprofileservice.dtos.response.common.AddressResponse;
import com.intuit.interview.businessprofileservice.dtos.response.common.TaxIdentifiersResponse;
import com.intuit.interview.businessprofileservice.models.common.Address;
import com.intuit.interview.businessprofileservice.models.common.TaxIdentifiers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BusinessProfileCommonTransformerTest {

    @Test
    public void testConvertAddressDtoToAddress() {
        AddressDto addressDto = new AddressDto(
                "123 Main St",
                "Suite 456",
                "TestCity",
                "12345",
                "TS",
                "TestCountry"
        );

        Address address = BusinessProfileCommonTransformer.convertAddressDtoToAddress(addressDto);

        assertEquals(addressDto.getLine1(), address.getLine1());
        assertEquals(addressDto.getLine2(), address.getLine2());
        assertEquals(addressDto.getCity(), address.getCity());
        assertEquals(addressDto.getZip(), address.getZip());
        assertEquals(addressDto.getState(), address.getState());
        assertEquals(addressDto.getCountry(), address.getCountry());
    }

    @Test
    public void testConvertTaxIdentifiersDtoToTaxIdentifiers() {
        TaxIdentifiersDto taxIdentifiersDto = new TaxIdentifiersDto(
                "ABCDE1234F",
                "123456789"
        );

        TaxIdentifiers taxIdentifiers = BusinessProfileCommonTransformer.convertTaxIdentifiersDtoToTaxIdentifiers(taxIdentifiersDto);

        assertEquals(taxIdentifiersDto.getPan(), taxIdentifiers.getPan());
        assertEquals(taxIdentifiersDto.getEin(), taxIdentifiers.getEin());
    }

    @Test
    public void testConvertAddressToAddressResponse() {
        Address address = Address.builder()
                .line1("123 Main St")
                .line2("Suite 456")
                .city("TestCity")
                .zip("12345")
                .state("TS")
                .country("TestCountry")
                .build();

        AddressResponse addressResponse = BusinessProfileCommonTransformer.convertAddressToAddressResponse(address);

        assertEquals(address.getLine1(), addressResponse.getLine1());
        assertEquals(address.getLine2(), addressResponse.getLine2());
        assertEquals(address.getCity(), addressResponse.getCity());
        assertEquals(address.getZip(), addressResponse.getZip());
        assertEquals(address.getState(), addressResponse.getState());
        assertEquals(address.getCountry(), addressResponse.getCountry());
    }

    @Test
    public void testConvertTaxIdentifiersToTaxIdentifiersResponse() {
        TaxIdentifiers taxIdentifiers = TaxIdentifiers.builder()
                .pan("ABCDE1234F")
                .ein("123456789")
                .build();

        TaxIdentifiersResponse taxIdentifiersResponse =
                BusinessProfileCommonTransformer.convertTaxIdentifiersToTaxIdentifiersResponse(taxIdentifiers);

        assertEquals(taxIdentifiers.getPan(), taxIdentifiersResponse.getPan());
        assertEquals(taxIdentifiers.getEin(), taxIdentifiersResponse.getEin());
    }
}

