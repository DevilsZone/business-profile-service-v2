package com.intuit.interview.businessprofileservice.dtos.transformers;

import com.intuit.interview.businessprofileservice.annotations.Transformer;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileCreateDto;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileUpdateDto;
import com.intuit.interview.businessprofileservice.dtos.response.BusinessProfileResponse;
import com.intuit.interview.businessprofileservice.models.BusinessProfile;

import java.time.Instant;

@Transformer(value = "BusinessProfileTransformer")
public class BusinessProfileTransformer {

    public static BusinessProfile convertBusinessProfileCreateDtoToBusinessProfile(
            BusinessProfileCreateDto businessProfileCreateDto) {
        BusinessProfile businessProfile = BusinessProfile.builder()
                .companyName(businessProfileCreateDto.getCompanyName())
                .legalName(businessProfileCreateDto.getLegalName())
                .email(businessProfileCreateDto.getEmail())
                .website(businessProfileCreateDto.getWebsite())
                .businessAddress(BusinessProfileCommonTransformer.convertAddressDtoToAddress(
                        businessProfileCreateDto.getBusinessAddress()))
                .legalAddress(BusinessProfileCommonTransformer.convertAddressDtoToAddress(
                        businessProfileCreateDto.getLegalAddress()))
                .taxIdentifiers(BusinessProfileCommonTransformer.convertTaxIdentifiersDtoToTaxIdentifiers(
                        businessProfileCreateDto.getTaxIdentifiers()))
                .build();
        businessProfile.setVersion(1L);
        businessProfile.setCreatedAt(Instant.now().toEpochMilli());
        businessProfile.setUpdatedAt(Instant.now().toEpochMilli());
        businessProfile.setCreatedBy(businessProfileCreateDto.getCreatedBy());
        businessProfile.setUpdatedBy(businessProfileCreateDto.getCreatedBy());
        return businessProfile;
    }

    public static BusinessProfileResponse convertBusinessProfileToBusinessProfileResponse(
            BusinessProfile businessProfile) {
        return BusinessProfileResponse.builder()
                .companyName(businessProfile.getCompanyName())
                .legalName(businessProfile.getLegalName())
                .email(businessProfile.getEmail())
                .website(businessProfile.getWebsite())
                .businessAddress(BusinessProfileCommonTransformer.convertAddressToAddressResponse(
                        businessProfile.getBusinessAddress()))
                .legalAddress(BusinessProfileCommonTransformer.convertAddressToAddressResponse(
                        businessProfile.getLegalAddress()))
                .taxIdentifiers(BusinessProfileCommonTransformer.convertTaxIdentifiersToTaxIdentifiersResponse(
                        businessProfile.getTaxIdentifiers()))
                .build();
    }

    public static BusinessProfile getUpdatedBusinessProfileForUpdateRequest(
            BusinessProfile businessProfile, BusinessProfileUpdateDto businessProfileUpdateDto
    ) {

        if (businessProfileUpdateDto.getCompanyName() != null) {
            businessProfile.setCompanyName(businessProfileUpdateDto.getCompanyName());
        }
        if (businessProfileUpdateDto.getEmail() != null) {
            businessProfile.setEmail(businessProfileUpdateDto.getEmail());
        }
        if (businessProfileUpdateDto.getWebsite() != null) {
            businessProfile.setWebsite(businessProfileUpdateDto.getWebsite());
        }
        if (businessProfileUpdateDto.getBusinessAddress() != null) {
            businessProfile.setBusinessAddress(BusinessProfileCommonTransformer.convertAddressDtoToAddress(
                    businessProfileUpdateDto.getBusinessAddress()
            ));
        }
        if (businessProfileUpdateDto.getLegalAddress() != null) {
            businessProfile.setLegalAddress(BusinessProfileCommonTransformer.convertAddressDtoToAddress(
                    businessProfileUpdateDto.getLegalAddress()
            ));
        }
        if (businessProfileUpdateDto.getTaxIdentifiers() != null) {
            businessProfile.setTaxIdentifiers(BusinessProfileCommonTransformer.convertTaxIdentifiersDtoToTaxIdentifiers(
                    businessProfileUpdateDto.getTaxIdentifiers()
            ));
        }
        businessProfile.setUpdatedBy(businessProfile.getUpdatedBy());
        businessProfile.setUpdatedAt(Instant.now().toEpochMilli());
        businessProfile.setVersion(businessProfile.getVersion()+1);
        return businessProfile;
    }


}
