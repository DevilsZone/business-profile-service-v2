package com.intuit.interview.businessprofileservice.external.validations.impl;

import com.intuit.interview.businessprofileservice.configurations.ValidationConfiguration;
import com.intuit.interview.businessprofileservice.external.validations.ProductValidationService;
import com.intuit.interview.businessprofileservice.models.BusinessProfile;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class QuickBookValidationServiceImpl implements ProductValidationService {
    private final WebClient webClient;

    private final String createUri;

    private final String updateUri;

    private final String deleteUri;

    @Autowired
    public QuickBookValidationServiceImpl(
            WebClient.Builder webClientBuilder,
            ValidationConfiguration validationConfiguration
    ) {
        this.webClient = webClientBuilder.baseUrl(
                validationConfiguration.getQuickbookBaseUrl()
        ).build();
        this.createUri = validationConfiguration.getQuickbookCreateUri();
        this.updateUri = validationConfiguration.getQuickbookUpdateUri();
        this.deleteUri = validationConfiguration.getQuickbookDeleteUri();
    }

    @Override
    @CircuitBreaker(name = "quickBookValidationServiceImplCreate", fallbackMethod = "fallbackForValidate")
    @Bulkhead(name = "quickBookValidationServiceImplCreate")
    public Mono<Void> validateBusinessProfileForCreate(BusinessProfile businessProfile) {
        return webClient.post()
                .uri(createUri)
                .bodyValue(businessProfile)
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    @CircuitBreaker(name = "quickBookValidationServiceImplUpdate", fallbackMethod = "fallbackForValidate")
    @Bulkhead(name = "quickBookValidationServiceImplUpdate")
    public Mono<Void> validateBusinessProfileForUpdate(BusinessProfile businessProfile) {
        return webClient.post()
                .uri(updateUri)
                .bodyValue(businessProfile)
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    @CircuitBreaker(name = "quickBookValidationServiceImplDelete", fallbackMethod = "fallbackForValidate")
    @Bulkhead(name = "quickBookValidationServiceImplDelete")
    public Mono<Void> validateBusinessProfileForDelete(BusinessProfile businessProfile) {
        return webClient.post()
                .uri(deleteUri)
                .bodyValue(businessProfile)
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    public Mono<Void> fallbackForValidate(BusinessProfile businessProfile, Throwable throwable) {
        return Mono.error(throwable);
    }
}
