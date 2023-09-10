package com.intuit.interview.businessprofileservice.services.impl;

import com.intuit.interview.businessprofileservice.builders.BusinessProfileBuilderForTests;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileDeleteDto;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileProductsUpdateDto;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileUpdateDto;
import com.intuit.interview.businessprofileservice.dtos.response.BusinessProfileResponse;
import com.intuit.interview.businessprofileservice.enums.Product;
import com.intuit.interview.businessprofileservice.exceptions.AppException;
import com.intuit.interview.businessprofileservice.models.BusinessProfile;
import com.intuit.interview.businessprofileservice.models.common.BusinessProfileProduct;
import com.intuit.interview.businessprofileservice.repositories.BusinessProfileRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class BusinessProfileServiceImplTest {

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
    void testCreateBusinessProfile() {
        when(businessProfileRepository.getBusinessProfileByLegalName(any())).thenReturn(null);
        when(businessProfileRepository.save(any())).thenReturn(BusinessProfileBuilderForTests.createBusinessProfile());

        BusinessProfileResponse response = businessProfileService.createBusinessProfile(
                BusinessProfileBuilderForTests.createBusinessProfileDto()
        );

        assertNotNull(response); // Add more assertions based on expected response
    }

    @Test
    void testCreateBusinessProfile_Failure() {
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
    void testUpdateBusinessProfile() {
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
    void testUpdateBusinessProfile_Failure() {
        when(businessProfileRepository.getBusinessProfileByLegalName(any())).thenReturn(null);
        assertThrows(
                AppException.class,
                () -> businessProfileService.updateBusinessProfile(
                        BusinessProfileBuilderForTests.createBusinessProfileUpdateDto()
                )
        );
    }

    @Test
    void testGetBusinessProfileByLegalName() {
        when(businessProfileRepository.getBusinessProfileByLegalName(any())).thenReturn(
                BusinessProfileBuilderForTests.createBusinessProfile()
        );

        BusinessProfileResponse response = businessProfileService.getBusinessProfileByLegalName(
                BusinessProfileBuilderForTests.createBusinessProfile().getLegalName()
        );

        assertNotNull(response); // Add more assertions based on expected response
    }

    @Test
    void testGetBusinessProfileByLegalName_Failure() {
        when(businessProfileRepository.getBusinessProfileByLegalName(any())).thenReturn(null);
        assertThrows(AppException.class, () -> businessProfileService.getBusinessProfileByLegalName(any()));
    }

    @Test
    void testDeleteBusinessProfile() {
        BusinessProfileDeleteDto deleteDto = new BusinessProfileDeleteDto(); // Populate with mock data if necessary
        when(businessProfileRepository.getBusinessProfileByLegalName(any())).thenReturn(
                BusinessProfileBuilderForTests.createBusinessProfile());
        when(businessProfileRepository.deleteBusinessProfileByLegalName(any())).thenReturn(1L);

        boolean isDeleted = businessProfileService.deleteBusinessProfile(deleteDto);

        assertTrue(isDeleted);
    }

    @Test
    void testDeleteBusinessProfile_Failure() {
        BusinessProfileDeleteDto deleteDto = new BusinessProfileDeleteDto(); // Populate with mock data if necessary
        when(businessProfileRepository.getBusinessProfileByLegalName(any())).thenReturn(
                BusinessProfileBuilderForTests.createBusinessProfile());
        when(businessProfileRepository.deleteBusinessProfileByLegalName(deleteDto.getLegalName())).thenReturn(0L);

        boolean isDeleted = businessProfileService.deleteBusinessProfile(deleteDto);

        assertFalse(isDeleted);
    }

    @Test
    void testDeleteBusinessProfile_Failure_Fetch_Profile() {
        BusinessProfileDeleteDto deleteDto = new BusinessProfileDeleteDto(); // Populate with mock data if necessary
        when(businessProfileRepository.getBusinessProfileByLegalName(any())).thenThrow(AppException.class);

        assertThrows(AppException.class, () -> businessProfileService.deleteBusinessProfile(deleteDto));
    }

    @Test
    void testDeleteBusinessProfile_Failure_Fetch_Profile_Null() {
        BusinessProfileDeleteDto deleteDto = new BusinessProfileDeleteDto(); // Populate with mock data if necessary
        when(businessProfileRepository.getBusinessProfileByLegalName(any())).thenReturn(null);

        assertThrows(AppException.class, () -> businessProfileService.deleteBusinessProfile(deleteDto));
    }

    @Test
    void testUpdateBusinessProfileProducts() {
        BusinessProfile businessProfile = BusinessProfileBuilderForTests.createBusinessProfile();
        Set<BusinessProfileProduct> existingProducts = new HashSet<>();
        existingProducts.add(BusinessProfileProduct.builder().product(Product.QUICK_BOOK_PAYROLL).build());
        businessProfile.setBusinessProfileProducts(existingProducts);
        BusinessProfileProductsUpdateDto businessProfileProductsUpdateDto = new BusinessProfileProductsUpdateDto();
        businessProfileProductsUpdateDto.setLegalName(businessProfile.getLegalName());
        List<Product> addedProducts = new ArrayList<>();
        List<Product> removedProducts = new ArrayList<>();
        addedProducts.add(Product.QUICK_BOOK);
        removedProducts.add(Product.QUICK_BOOK_PAYROLL);
        businessProfileProductsUpdateDto.setProductsToBeAdded(addedProducts);
        businessProfileProductsUpdateDto.setProductsToBeRemoved(removedProducts);
        when(businessProfileRepository.getBusinessProfileByLegalName(any())).thenReturn(businessProfile);
        when(businessProfileRepository.save(any())).thenReturn(businessProfile);

        businessProfileService.updateBusinessProfileProducts(businessProfileProductsUpdateDto);

        verify(businessProfileRepository, times(1)).getBusinessProfileByLegalName(any());
        verify(businessProfileRepository, times(1)).save(any());
    }

    @Test
    void testUpdateBusinessProfileProducts_Failure() {
        BusinessProfile businessProfile = BusinessProfileBuilderForTests.createBusinessProfile();
        BusinessProfileProductsUpdateDto businessProfileProductsUpdateDto = new BusinessProfileProductsUpdateDto();
        businessProfileProductsUpdateDto.setLegalName(businessProfile.getLegalName());
        List<Product> addedProducts = new ArrayList<>();
        List<Product> removedProducts = new ArrayList<>();
        addedProducts.add(Product.QUICK_BOOK);
        removedProducts.add(Product.QUICK_BOOK_PAYROLL);
        businessProfileProductsUpdateDto.setProductsToBeAdded(addedProducts);
        businessProfileProductsUpdateDto.setProductsToBeRemoved(removedProducts);
        when(businessProfileRepository.getBusinessProfileByLegalName(any())).thenReturn(null);

        assertThrows(
                AppException.class,
                () -> businessProfileService.updateBusinessProfileProducts(
                        businessProfileProductsUpdateDto
                )
        );
    }

    @Test
    void testUpdateBusinessProfileProducts_Failure_EmptyList() {
        BusinessProfile businessProfile = BusinessProfileBuilderForTests.createBusinessProfile();
        BusinessProfileProductsUpdateDto businessProfileProductsUpdateDto = new BusinessProfileProductsUpdateDto();
        businessProfileProductsUpdateDto.setLegalName(businessProfile.getLegalName());
        List<Product> addedProducts = new ArrayList<>();
        List<Product> removedProducts = new ArrayList<>();
        businessProfileProductsUpdateDto.setProductsToBeAdded(addedProducts);
        businessProfileProductsUpdateDto.setProductsToBeRemoved(removedProducts);

        assertThrows(
                AppException.class,
                () -> businessProfileService.updateBusinessProfileProducts(
                        businessProfileProductsUpdateDto
                )
        );
    }

}

