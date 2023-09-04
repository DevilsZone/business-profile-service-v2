package com.intuit.interview.businessprofileservice.services.impl;

import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileCreateDto;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileDeleteDto;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileUpdateDto;
import com.intuit.interview.businessprofileservice.dtos.response.BusinessProfileResponse;
import com.intuit.interview.businessprofileservice.dtos.transformers.BusinessProfileTransformer;
import com.intuit.interview.businessprofileservice.enums.ErrorCause;
import com.intuit.interview.businessprofileservice.exceptions.AppException;
import com.intuit.interview.businessprofileservice.external.validations.ProductValidationService;
import com.intuit.interview.businessprofileservice.models.BusinessProfile;
import com.intuit.interview.businessprofileservice.repositories.BusinessProfileRepository;
import com.intuit.interview.businessprofileservice.services.BusinessProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessProfileServiceImpl implements BusinessProfileService {
    private final BusinessProfileRepository businessProfileRepository;

    private final List<ProductValidationService> productValidationServiceList;

    @Autowired
    public BusinessProfileServiceImpl(
            BusinessProfileRepository businessProfileRepository,
            List<ProductValidationService> productValidationServiceList
    ) {
        this.businessProfileRepository = businessProfileRepository;
        this.productValidationServiceList = productValidationServiceList;
    }

    @Override
    public BusinessProfileResponse createBusinessProfile(BusinessProfileCreateDto createDto) {
        BusinessProfile existingBusinessProfile = businessProfileRepository.getBusinessProfileByLegalName(
                createDto.getLegalName()
        );
        if (existingBusinessProfile != null) {
            throw new AppException(ErrorCause.EXISTING_BUSINESS_PROFILE.name());
        }
        BusinessProfile businessProfile =
                BusinessProfileTransformer.convertBusinessProfileCreateDtoToBusinessProfile(createDto);
        validateBusinessProfileForCreate(businessProfile);
        return BusinessProfileTransformer.convertBusinessProfileToBusinessProfileResponse(
                businessProfileRepository.save(businessProfile)
        );
    }

    @Override
    public BusinessProfileResponse getBusinessProfileByLegalName(String legalName) {
        Optional<BusinessProfile> businessProfile = Optional.ofNullable(
                businessProfileRepository.getBusinessProfileByLegalName(legalName)
        );
        if (businessProfile.isPresent()) {
            return BusinessProfileTransformer.convertBusinessProfileToBusinessProfileResponse(businessProfile.get());
        }
        throw new AppException(ErrorCause.BUSINESS_PROFILE_NOT_FOUND.name());
    }

    @Override
    public BusinessProfileResponse updateBusinessProfile(BusinessProfileUpdateDto updateDto) {
        BusinessProfile businessProfile =
                businessProfileRepository.getBusinessProfileByLegalName(updateDto.getLegalName());
        if (businessProfile == null) {
            throw new AppException(ErrorCause.BUSINESS_PROFILE_NOT_FOUND.name());
        }
        BusinessProfile updatedBusinessProfile = BusinessProfileTransformer.getUpdatedBusinessProfileForUpdateRequest(
                businessProfile, updateDto
        );
        validateBusinessProfileForUpdate(updatedBusinessProfile);
        return BusinessProfileTransformer.convertBusinessProfileToBusinessProfileResponse(
                businessProfileRepository.save(updatedBusinessProfile)
        );
    }

    @Override
    public boolean deleteBusinessProfile(BusinessProfileDeleteDto deleteDto) {
        BusinessProfile businessProfile = businessProfileRepository.getBusinessProfileByLegalName(
                deleteDto.getLegalName()
        );
        if (businessProfile == null) {
            throw new AppException(ErrorCause.BUSINESS_PROFILE_NOT_FOUND.name());
        }
        validateBusinessProfileForDelete(businessProfile);
        Long response = businessProfileRepository.deleteBusinessProfileByLegalName(businessProfile.getLegalName());
        return response > 0;
    }

    public void validateBusinessProfileForCreate(BusinessProfile businessProfile) {
        List<Mono<Void>> futures = new ArrayList<>();
        for (ProductValidationService service : productValidationServiceList) {
            Mono<Void> validation = service.validateBusinessProfileForCreate(businessProfile)
                    .subscribeOn(Schedulers.boundedElastic());
            futures.add(validation);
        }
        try {
            Mono.when(futures).block();
        } catch (Exception e) {
            throw new AppException(ErrorCause.VALIDATION_FAILED.name());
        }
    }

    public void validateBusinessProfileForUpdate(BusinessProfile businessProfile) {
        List<Mono<Void>> futures = new ArrayList<>();
        for (ProductValidationService service : productValidationServiceList) {
            Mono<Void> validation = service.validateBusinessProfileForUpdate(businessProfile)
                    .subscribeOn(Schedulers.boundedElastic());
            futures.add(validation);
        }
        try {
            Mono.when(futures).block();
        } catch (Exception e) {
            throw new AppException(ErrorCause.VALIDATION_FAILED.name());
        }
    }

    public void validateBusinessProfileForDelete(BusinessProfile businessProfile) {
        List<Mono<Void>> futures = new ArrayList<>();
        for (ProductValidationService service : productValidationServiceList) {
            Mono<Void> validation = service.validateBusinessProfileForDelete(businessProfile)
                    .subscribeOn(Schedulers.boundedElastic());
            futures.add(validation);
        }
        try {
            Mono.when(futures).block();
        } catch (Exception e) {
            throw new AppException(ErrorCause.VALIDATION_FAILED.name());
        }
    }

}
