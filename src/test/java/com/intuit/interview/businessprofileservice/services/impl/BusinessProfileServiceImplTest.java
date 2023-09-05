package com.intuit.interview.businessprofileservice.services.impl;

import com.intuit.interview.businessprofileservice.builders.BusinessProfileBuilderForTests;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileDeleteDto;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileUpdateDto;
import com.intuit.interview.businessprofileservice.dtos.response.BusinessProfileResponse;
import com.intuit.interview.businessprofileservice.exceptions.AppException;
import com.intuit.interview.businessprofileservice.repositories.BusinessProfileRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class BusinessProfileServiceImplTest {

    @Mock
    private BusinessProfileRepository businessProfileRepository;

    @InjectMocks
    @Spy
    private BusinessProfileServiceImpl businessProfileService;

    @BeforeEach
    public void setup() {
        // Mocking Validation Services
        doNothing().when(businessProfileService).validateBusinessProfileForDelete(any());
        doNothing().when(businessProfileService).validateBusinessProfileForUpdate(any());
        doNothing().when(businessProfileService).validateBusinessProfileForCreate(any());
    }

    @AfterEach
    public void clear() {
        clearAllCaches();
    }

    @Test
    public void testCreateBusinessProfile() {
        when(businessProfileRepository.getBusinessProfileByLegalName(any())).thenReturn(null);
        when(businessProfileRepository.save(any())).thenReturn(BusinessProfileBuilderForTests.createBusinessProfile());

        BusinessProfileResponse response = businessProfileService.createBusinessProfile(
                BusinessProfileBuilderForTests.createBusinessProfileDto()
        );

        assertNotNull(response); // Add more assertions based on expected response
    }

    @Test
    public void testCreateBusinessProfile_Failure() {
        when(businessProfileRepository.getBusinessProfileByLegalName(any())).thenReturn(
                BusinessProfileBuilderForTests.createBusinessProfile());
        assertThrows(
                AppException.class,
                () -> businessProfileService.createBusinessProfile(
                        BusinessProfileBuilderForTests.createBusinessProfileDto()
                )
        );
    }

    @Test
    public void testUpdateBusinessProfile() {
        BusinessProfileUpdateDto updateDto =
                BusinessProfileBuilderForTests.createBusinessProfileUpdateDto(); // Populate this with mock data
        when(businessProfileRepository.getBusinessProfileByLegalName(any())).thenReturn(
                BusinessProfileBuilderForTests.createBusinessProfile()
        );
        when(businessProfileRepository.save(any())).thenReturn(BusinessProfileBuilderForTests.createBusinessProfile());

        BusinessProfileResponse response = businessProfileService.updateBusinessProfile(updateDto);

        assertNotNull(response); // Add more assertions based on expected response
    }

    @Test
    public void testUpdateBusinessProfile_Failure() {
        when(businessProfileRepository.getBusinessProfileByLegalName(any())).thenReturn(null);
        assertThrows(
                AppException.class,
                () -> businessProfileService.updateBusinessProfile(
                        BusinessProfileBuilderForTests.createBusinessProfileUpdateDto()
                )
        );
    }

    @Test
    public void testGetBusinessProfileByLegalName() {
        when(businessProfileRepository.getBusinessProfileByLegalName(any())).thenReturn(
                BusinessProfileBuilderForTests.createBusinessProfile()
        );

        BusinessProfileResponse response = businessProfileService.getBusinessProfileByLegalName(
                BusinessProfileBuilderForTests.createBusinessProfile().getLegalName()
        );

        assertNotNull(response); // Add more assertions based on expected response
    }

    @Test
    public void testGetBusinessProfileByLegalName_Failure() {
        when(businessProfileRepository.getBusinessProfileByLegalName(any())).thenReturn(null);
        assertThrows(AppException.class, () -> businessProfileService.getBusinessProfileByLegalName(any()));
    }

    @Test
    public void testDeleteBusinessProfile() {
        BusinessProfileDeleteDto deleteDto = new BusinessProfileDeleteDto(); // Populate with mock data if necessary
        when(businessProfileRepository.getBusinessProfileByLegalName(any())).thenReturn(
                BusinessProfileBuilderForTests.createBusinessProfile());
        when(businessProfileRepository.deleteBusinessProfileByLegalName(any())).thenReturn(1L);

        boolean isDeleted = businessProfileService.deleteBusinessProfile(deleteDto);

        assertTrue(isDeleted);
    }

    @Test
    public void testDeleteBusinessProfile_Failure() {
        BusinessProfileDeleteDto deleteDto = new BusinessProfileDeleteDto(); // Populate with mock data if necessary
        when(businessProfileRepository.getBusinessProfileByLegalName(any())).thenReturn(
                BusinessProfileBuilderForTests.createBusinessProfile());
        when(businessProfileRepository.deleteBusinessProfileByLegalName(deleteDto.getLegalName())).thenReturn(0L);

        boolean isDeleted = businessProfileService.deleteBusinessProfile(deleteDto);

        assertFalse(isDeleted);
    }

}

