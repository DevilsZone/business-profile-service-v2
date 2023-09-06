package com.intuit.interview.businessprofileservice.services;

import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileCreateDto;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileDeleteDto;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileProductsUpdateDto;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileUpdateDto;
import com.intuit.interview.businessprofileservice.dtos.response.BusinessProfileResponse;

public interface BusinessProfileService {

    /**
     * Create a new BusinessProfile using the provided DTO.
     *
     * @param createDto Data to create a new BusinessProfile.
     * @return The created BusinessProfileResponse.
     */
    BusinessProfileResponse createBusinessProfile(BusinessProfileCreateDto createDto);

    /**
     * Retrieve a BusinessProfile based on the company name.
     *
     * @param legalName The legal company name of the BusinessProfile to retrieve.
     * @return An Optional containing the found BusinessProfileResponse, or empty if not found.
     */
    BusinessProfileResponse getBusinessProfileByLegalName(String legalName);

    /**
     * Update an existing BusinessProfile using the provided DTO.
     *
     * @param updateDto Data to update an existing BusinessProfile.
     * @return The updated BusinessProfileResponse, or null if the operation was unsuccessful.
     */
    BusinessProfileResponse updateBusinessProfile(BusinessProfileUpdateDto updateDto);

    /**
     * Update an existing BusinessProfile using the provided DTO.
     *
     * @param businessProfileProductsUpdateDto Data to update applicable services an existing BusinessProfile.
     */
    void updateBusinessProfileProducts(BusinessProfileProductsUpdateDto businessProfileProductsUpdateDto);

    /**
     * Delete a BusinessProfile based on the company name.
     *
     * @param deleteDto Contains the company name of the BusinessProfile to delete.
     * @return true if the deletion was successful, false otherwise.
     */
    boolean deleteBusinessProfile(BusinessProfileDeleteDto deleteDto);
}
