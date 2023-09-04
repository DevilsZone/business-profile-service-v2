package com.intuit.interview.businessprofileservice.external.validations;

import com.intuit.interview.businessprofileservice.models.BusinessProfile;
import reactor.core.publisher.Mono;

public interface ProductValidationService {
    Mono<Void> validateBusinessProfileForCreate(BusinessProfile businessProfile);

    Mono<Void> validateBusinessProfileForUpdate(BusinessProfile businessProfile);

    Mono<Void> validateBusinessProfileForDelete(BusinessProfile businessProfile);

    Mono<Void> fallbackForValidate(BusinessProfile businessProfile, Throwable throwable);
}

