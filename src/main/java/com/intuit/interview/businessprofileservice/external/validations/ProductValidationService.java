package com.intuit.interview.businessprofileservice.external.validations;

import com.intuit.interview.businessprofileservice.models.BusinessProfile;
import com.intuit.interview.businessprofileservice.models.common.BusinessProfileProduct;
import reactor.core.publisher.Mono;

public interface ProductValidationService {
    BusinessProfileProduct getProduct();
    Mono<Void> validateBusinessProfileForCreate(BusinessProfile businessProfile);

    Mono<Void> validateBusinessProfileForUpdate(BusinessProfile businessProfile);

    Mono<Void> validateBusinessProfileForDelete(BusinessProfile businessProfile);

    Mono<Void> fallbackForValidate(BusinessProfile businessProfile, Throwable throwable);
}

