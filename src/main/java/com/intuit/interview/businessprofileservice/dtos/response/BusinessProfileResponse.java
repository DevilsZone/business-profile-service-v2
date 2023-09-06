package com.intuit.interview.businessprofileservice.dtos.response;

import com.intuit.interview.businessprofileservice.dtos.response.common.AddressResponse;
import com.intuit.interview.businessprofileservice.dtos.response.common.TaxIdentifiersResponse;
import com.intuit.interview.businessprofileservice.models.common.BusinessProfileProduct;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BusinessProfileResponse {
    private String companyName;

    private String legalName;

    private String email;

    private String website;

    private AddressResponse businessAddress;

    private AddressResponse legalAddress;

    private TaxIdentifiersResponse taxIdentifiers;

    private Set<BusinessProfileProduct> profileProductList;

    private String createdBy;
}
