package com.intuit.interview.businessprofileservice.dtos.transformers;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class BusinessProfileTransformerTest {

    private BusinessProfileCreateDto businessProfileCreateDto;

    private BusinessProfile businessProfile;

    private BusinessProfileUpdateDto businessProfileUpdateDto;

    @BeforeEach
    public void setup() {
        // 1. Set up sample data
        AddressDto addressDto = new AddressDto();
        addressDto.setLine1("123");
        addressDto.setLine2("123");
        addressDto.setCity("123");
        addressDto.setZip("123");
        addressDto.setState("123");
        addressDto.setCountry("123");

        TaxIdentifiersDto taxIdentifiersDto = new TaxIdentifiersDto();
        taxIdentifiersDto.setPan("123");
        taxIdentifiersDto.setEin("123");

        // Populate BusinessProfileCreateDto
        businessProfileCreateDto = new BusinessProfileCreateDto();
        businessProfileCreateDto.setCompanyName("Test Company");
        businessProfileCreateDto.setLegalName("Test Legal");
        businessProfileCreateDto.setEmail("test@example.com");
        businessProfileCreateDto.setWebsite("https://test.com");
        businessProfileCreateDto.setBusinessAddress(addressDto);
        businessProfileCreateDto.setLegalAddress(addressDto);
        businessProfileCreateDto.setTaxIdentifiers(taxIdentifiersDto);

        // Populate BusinessProfile
        businessProfile = new BusinessProfile();
        businessProfile.setCompanyName("Old Company");
        businessProfile.setLegalName("Old Legal");
        businessProfile.setEmail("old@example.com");
        businessProfile.setWebsite("https://old.com");
        businessProfile.setVersion(1L);

        // Populate BusinessProfileUpdateDto
        businessProfileUpdateDto = new BusinessProfileUpdateDto();
        businessProfileUpdateDto.setCompanyName("Updated Company");
        businessProfileUpdateDto.setLegalName("Updated Legal");
        businessProfileUpdateDto.setEmail("updated@example.com");
        businessProfileUpdateDto.setWebsite("https://updated.com");
    }

    @Test
    public void testConvertBusinessProfileCreateDtoToBusinessProfile() {
        try (MockedStatic<BusinessProfileCommonTransformer> utilities = Mockito.mockStatic(
                BusinessProfileCommonTransformer.class)) {
            utilities.when(() -> BusinessProfileCommonTransformer.convertAddressDtoToAddress(any()))
                    .thenReturn(new Address());
            utilities.when(() -> BusinessProfileCommonTransformer.convertTaxIdentifiersDtoToTaxIdentifiers(any()))
                    .thenReturn(new TaxIdentifiers());

            BusinessProfile result = BusinessProfileTransformer.convertBusinessProfileCreateDtoToBusinessProfile(
                    businessProfileCreateDto);

            assertNotNull(result);
            assertEquals(businessProfileCreateDto.getCompanyName(), result.getCompanyName());
            assertEquals(businessProfileCreateDto.getLegalName(), result.getLegalName());
            assertEquals(businessProfileCreateDto.getEmail(), result.getEmail());
            assertEquals(businessProfileCreateDto.getWebsite(), result.getWebsite());
        }
    }

    @Test
    public void testConvertBusinessProfileToBusinessProfileResponse() {
        try (MockedStatic<BusinessProfileCommonTransformer> utilities = Mockito.mockStatic(
                BusinessProfileCommonTransformer.class)) {
            utilities.when(() -> BusinessProfileCommonTransformer.convertAddressToAddressResponse(any()))
                    .thenReturn(AddressResponse.builder().build());
            utilities.when(() -> BusinessProfileCommonTransformer.convertTaxIdentifiersToTaxIdentifiersResponse(any()))
                    .thenReturn(
                            TaxIdentifiersResponse.builder().build()
                    );

            BusinessProfileResponse result =
                    BusinessProfileTransformer.convertBusinessProfileToBusinessProfileResponse(businessProfile);

            assertNotNull(result);
            assertEquals(businessProfile.getCompanyName(), result.getCompanyName());
            assertEquals(businessProfile.getLegalName(), result.getLegalName());
            assertEquals(businessProfile.getEmail(), result.getEmail());
            assertEquals(businessProfile.getWebsite(), result.getWebsite());
        }
    }

    @Test
    public void testGetUpdatedBusinessProfileForUpdateRequest() {
        try (MockedStatic<BusinessProfileCommonTransformer> utilities = Mockito.mockStatic(
                BusinessProfileCommonTransformer.class)) {
            utilities.when(() -> BusinessProfileCommonTransformer.convertAddressDtoToAddress(any()))
                    .thenReturn(new Address());
            utilities.when(() -> BusinessProfileCommonTransformer.convertTaxIdentifiersDtoToTaxIdentifiers(any()))
                    .thenReturn(new TaxIdentifiers());

            BusinessProfile result =
                    BusinessProfileTransformer.getUpdatedBusinessProfileForUpdateRequest(businessProfile,
                            businessProfileUpdateDto);

            assertNotNull(result);
            assertEquals(businessProfileUpdateDto.getCompanyName(), result.getCompanyName());
            assertEquals(businessProfile.getLegalName(), result.getLegalName());
            assertEquals(businessProfileUpdateDto.getEmail(), result.getEmail());
            assertEquals(businessProfileUpdateDto.getWebsite(), result.getWebsite());
        }
    }
}

