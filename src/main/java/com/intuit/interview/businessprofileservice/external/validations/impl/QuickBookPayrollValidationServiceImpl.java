package com.intuit.interview.businessprofileservice.external.validations.impl;

import com.intuit.interview.businessprofileservice.configurations.ValidationConfiguration;
import com.intuit.interview.businessprofileservice.external.validations.ProductValidationService;
import com.intuit.interview.businessprofileservice.models.BusinessProfile;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class QuickBookPayrollValidationServiceImpl implements ProductValidationService {
    private final WebClient webClient;

    private final String createUri;

    private final String updateUri;

    private final String deleteUri;

    @Autowired
    public QuickBookPayrollValidationServiceImpl(
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
    @CircuitBreaker(name = "quickBookPayrollValidationServiceImplCreate", fallbackMethod = "fallbackForValidate")
    @Bulkhead(name = "quickBookPayrollValidationServiceImplCreate")
    public Mono<Void> validateBusinessProfileForCreate(BusinessProfile businessProfile) {
        return webClient.post()
                .uri(createUri)
                .bodyValue(businessProfile)
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    @CircuitBreaker(name = "quickBookPayrollValidationServiceImplUpdate", fallbackMethod = "fallbackForValidate")
    @Bulkhead(name = "quickBookPayrollValidationServiceImplUpdate")
    public Mono<Void> validateBusinessProfileForUpdate(BusinessProfile businessProfile) {
        return webClient.put()
                .uri(updateUri)
                .bodyValue(businessProfile)
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    @CircuitBreaker(name = "quickBookPayrollValidationServiceImplDelete", fallbackMethod = "fallbackForValidate")
    @Bulkhead(name = "quickBookPayrollValidationServiceImplDelete")
    public Mono<Void> validateBusinessProfileForDelete(BusinessProfile businessProfile) {
        return webClient.method(HttpMethod.DELETE)
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
