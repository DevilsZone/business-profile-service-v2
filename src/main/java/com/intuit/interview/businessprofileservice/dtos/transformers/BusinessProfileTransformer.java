package com.intuit.interview.businessprofileservice.dtos.transformers;

import com.intuit.interview.businessprofileservice.annotations.Transformer;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileCreateDto;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileProductsUpdateDto;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileUpdateDto;
import com.intuit.interview.businessprofileservice.dtos.response.BusinessProfileResponse;
import com.intuit.interview.businessprofileservice.models.BusinessProfile;
import com.intuit.interview.businessprofileservice.models.common.BusinessProfileProduct;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

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
        Set<BusinessProfileProduct> profileProducts = new HashSet<>();
        if (businessProfileCreateDto.getProduct() != null) {
            profileProducts.add(
                    BusinessProfileProduct.builder()
                            .product(businessProfileCreateDto.getProduct())
                            .build()
            );
        }
        businessProfile.setBusinessProfileProducts(profileProducts);
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
                .profileProductList(businessProfile.getBusinessProfileProducts())
                .build();
    }

    public static BusinessProfile getUpdatedBusinessProfileForUpdateRequest(
            BusinessProfile businessProfile, BusinessProfileUpdateDto businessProfileUpdateDto
    ) {
        if (businessProfileUpdateDto.getCompanyName() != null && !businessProfileUpdateDto.getCompanyName().equals(businessProfile.getCompanyName())) {
            businessProfile.setCompanyName(businessProfileUpdateDto.getCompanyName());
        }
        if (businessProfileUpdateDto.getEmail() != null && !businessProfileUpdateDto.getEmail().equals(businessProfile.getEmail())) {
            businessProfile.setEmail(businessProfileUpdateDto.getEmail());
        }
        if (businessProfileUpdateDto.getWebsite() != null && !businessProfileUpdateDto.getWebsite().equals(businessProfile.getWebsite())) {
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
        businessProfile.setVersion(businessProfile.getVersion() + 1);
        return businessProfile;
    }

    public static BusinessProfile manageBusinessProfileProducts(
            BusinessProfile businessProfile, BusinessProfileProductsUpdateDto businessProfileProductsUpdateDto
    ) {
        Set<BusinessProfileProduct> businessProfileServices = businessProfile.getBusinessProfileProducts();
        businessProfileServices.addAll(
                businessProfileProductsUpdateDto
                        .getProductsToBeAdded()
                        .stream()
                        .map(data ->
                                BusinessProfileProduct.builder().product(data).build()
                        )
                        .toList()
        );
        businessProfileProductsUpdateDto
                .getProductsToBeRemoved()
                .stream()
                .map(data ->
                        BusinessProfileProduct.builder().product(data).build()
                )
                .toList().forEach(
                        businessProfileServices::remove
        );
        businessProfile.setBusinessProfileProducts(businessProfileServices);
        return businessProfile;
    }


}
