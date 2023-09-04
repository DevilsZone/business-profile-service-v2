package com.intuit.interview.businessprofileservice.services.impl;

import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileCreateDto;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileDeleteDto;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileUpdateDto;
import com.intuit.interview.businessprofileservice.dtos.response.BusinessProfileResponse;
import com.intuit.interview.businessprofileservice.dtos.transformers.BusinessProfileTransformer;
import com.intuit.interview.businessprofileservice.enums.ErrorCause;
import com.intuit.interview.businessprofileservice.exceptions.AppException;
import com.intuit.interview.businessprofileservice.models.BusinessProfile;
import com.intuit.interview.businessprofileservice.repositories.BusinessProfileRepository;
import com.intuit.interview.businessprofileservice.services.BusinessProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessProfileServiceImpl implements BusinessProfileService {
    private final BusinessProfileRepository businessProfileRepository;

    @Autowired
    public BusinessProfileServiceImpl(
            BusinessProfileRepository businessProfileRepository
    ) {
        this.businessProfileRepository = businessProfileRepository;
    }

    @Override
    public BusinessProfileResponse createBusinessProfile(BusinessProfileCreateDto createDto) {
        BusinessProfile existingBusinessProfile = businessProfileRepository.getBusinessProfileByLegalName(
                createDto.getLegalName()
        );
        if (existingBusinessProfile == null) {
            throw new AppException(ErrorCause.EXISTING_BUSINESS_PROFILE.name());
        }
        return BusinessProfileTransformer.convertBusinessProfileToBusinessProfileResponse(
                businessProfileRepository.save(
                        BusinessProfileTransformer.convertBusinessProfileCreateDtoToBusinessProfile(createDto)
                )
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
        return BusinessProfileTransformer.convertBusinessProfileToBusinessProfileResponse(
                businessProfileRepository.save(BusinessProfileTransformer.getUpdatedBusinessProfileForUpdateRequest(
                        businessProfile, updateDto
                ))
        );
    }

    @Override
    public boolean deleteBusinessProfile(BusinessProfileDeleteDto deleteDto) {
        return businessProfileRepository.deleteBusinessProfileByLegalName(deleteDto.getLegalName());
    }
}
