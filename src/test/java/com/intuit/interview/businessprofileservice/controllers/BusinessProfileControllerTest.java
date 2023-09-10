package com.intuit.interview.businessprofileservice.controllers;

import com.intuit.interview.businessprofileservice.builders.BusinessProfileBuilderForTests;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileDeleteDto;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileProductsUpdateDto;
import com.intuit.interview.businessprofileservice.dtos.response.BusinessProfileResponse;
import com.intuit.interview.businessprofileservice.enums.ErrorCause;
import com.intuit.interview.businessprofileservice.exceptions.AppException;
import com.intuit.interview.businessprofileservice.services.BusinessProfileService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@Disabled
class BusinessProfileControllerTest {

    private final String uri = "/business-profile";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BusinessProfileService businessProfileService;

    @Test
    void getBusinessProfileByLegalName() throws Exception {
        BusinessProfileResponse businessProfileResponse =
                BusinessProfileBuilderForTests.createBusinessProfileResponse();
        // Mock the behavior of businessProfileService
        when(businessProfileService.getBusinessProfileByLegalName(any())).thenReturn(businessProfileResponse);

        ResultActions mvc = this.mockMvc.perform(
                MockMvcRequestBuilders.get(uri).queryParam("legalName", "Test Name")
        );
        mvc.andDo(print());
        mvc.andExpect(status().isOk());
        mvc.andExpect(redirectedUrl(null));
        mvc.andExpect(forwardedUrl(null));
        mvc.andExpect(MockMvcResultMatchers.jsonPath("$.data.legalName").value(businessProfileResponse.getLegalName()));
        mvc.andExpect(
                MockMvcResultMatchers.jsonPath("$.data.companyName").value(businessProfileResponse.getCompanyName()));
        mvc.andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value(businessProfileResponse.getEmail()));
        mvc.andExpect(MockMvcResultMatchers.jsonPath("$.data.website").value(businessProfileResponse.getWebsite()));
        mvc.andExpect(MockMvcResultMatchers.jsonPath("$.data.createdBy").value(businessProfileResponse.getCreatedBy()));
    }

    @Test
    void getBusinessProfileByLegalName_Failure() throws Exception {
        // Mock the behavior of businessProfileService
        when(businessProfileService.getBusinessProfileByLegalName(any())).thenThrow(
                new AppException(ErrorCause.BUSINESS_PROFILE_NOT_FOUND.name()));

        ResultActions mvc = this.mockMvc.perform(
                MockMvcRequestBuilders.get(uri).queryParam("legalName", "Test Name")
        );
        mvc.andDo(print());
        mvc.andExpect(status().is5xxServerError());
        mvc.andExpect(redirectedUrl(null));
        mvc.andExpect(forwardedUrl(null));
    }

    @Test
    void createBusinessProfile() throws Exception {
        BusinessProfileResponse businessProfileResponse =
                BusinessProfileBuilderForTests.createBusinessProfileResponse();
        // Mock the behavior of businessProfileService
        when(businessProfileService.createBusinessProfile(any())).thenReturn(businessProfileResponse);

        ResultActions mvc = this.mockMvc.perform(
                MockMvcRequestBuilders.post(uri).content(
                        new ObjectMapper().writeValueAsString(BusinessProfileBuilderForTests.createBusinessProfileDto())
                ).contentType(MediaType.APPLICATION_JSON)
        );
        mvc.andDo(print());
        mvc.andExpect(status().isOk());
        mvc.andExpect(redirectedUrl(null));
        mvc.andExpect(forwardedUrl(null));
        mvc.andExpect(MockMvcResultMatchers.jsonPath("$.data.legalName").value(businessProfileResponse.getLegalName()));
        mvc.andExpect(
                MockMvcResultMatchers.jsonPath("$.data.companyName").value(businessProfileResponse.getCompanyName()));
        mvc.andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value(businessProfileResponse.getEmail()));
        mvc.andExpect(MockMvcResultMatchers.jsonPath("$.data.website").value(businessProfileResponse.getWebsite()));
        mvc.andExpect(MockMvcResultMatchers.jsonPath("$.data.createdBy").value(businessProfileResponse.getCreatedBy()));
    }

    @Test
    void createBusinessProfile_Failure() throws Exception {
        // Mock the behavior of businessProfileService
        when(businessProfileService.createBusinessProfile(any())).thenThrow(
                new AppException(ErrorCause.EXISTING_BUSINESS_PROFILE.name()));

        ResultActions mvc = this.mockMvc.perform(
                MockMvcRequestBuilders.post(uri).content(
                        new ObjectMapper().writeValueAsString(BusinessProfileBuilderForTests.createBusinessProfileDto())
                ).contentType(MediaType.APPLICATION_JSON)
        );
        mvc.andDo(print());
        mvc.andExpect(status().is5xxServerError());
        mvc.andExpect(redirectedUrl(null));
        mvc.andExpect(forwardedUrl(null));
    }

    @Test
    void updateBusinessProfile() throws Exception {
        BusinessProfileResponse businessProfileResponse =
                BusinessProfileBuilderForTests.createBusinessProfileResponse();
        // Mock the behavior of businessProfileService
        when(businessProfileService.updateBusinessProfile(any())).thenReturn(businessProfileResponse);

        ResultActions mvc = this.mockMvc.perform(
                MockMvcRequestBuilders.put(uri).content(
                        new ObjectMapper().writeValueAsString(
                                BusinessProfileBuilderForTests.createBusinessProfileUpdateDto())
                ).contentType(MediaType.APPLICATION_JSON)
        );
        mvc.andDo(print());
        mvc.andExpect(status().isOk());
        mvc.andExpect(redirectedUrl(null));
        mvc.andExpect(forwardedUrl(null));
        mvc.andExpect(MockMvcResultMatchers.jsonPath("$.data.legalName").value(businessProfileResponse.getLegalName()));
        mvc.andExpect(
                MockMvcResultMatchers.jsonPath("$.data.companyName").value(businessProfileResponse.getCompanyName()));
        mvc.andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value(businessProfileResponse.getEmail()));
        mvc.andExpect(MockMvcResultMatchers.jsonPath("$.data.website").value(businessProfileResponse.getWebsite()));
        mvc.andExpect(MockMvcResultMatchers.jsonPath("$.data.createdBy").value(businessProfileResponse.getCreatedBy()));
    }

    @Test
    void updateBusinessProfile_Failure() throws Exception {
        // Mock the behavior of businessProfileService
        when(businessProfileService.updateBusinessProfile(any())).thenThrow(
                new AppException(ErrorCause.BUSINESS_PROFILE_NOT_FOUND.name()));

        ResultActions mvc = this.mockMvc.perform(
                MockMvcRequestBuilders.put(uri).content(
                        new ObjectMapper().writeValueAsString(
                                BusinessProfileBuilderForTests.createBusinessProfileUpdateDto())
                ).contentType(MediaType.APPLICATION_JSON)
        );
        mvc.andDo(print());
        mvc.andExpect(status().is5xxServerError());
        mvc.andExpect(redirectedUrl(null));
        mvc.andExpect(forwardedUrl(null));
    }

    @Test
    void deleteBusinessProfile() throws Exception {
        // Mock the behavior of businessProfileService
        when(businessProfileService.deleteBusinessProfile(any())).thenReturn(true);

        ResultActions mvc = this.mockMvc.perform(
                MockMvcRequestBuilders.delete(uri).content(
                        new ObjectMapper().writeValueAsString(new BusinessProfileDeleteDto("Test Name"))
                ).contentType(MediaType.APPLICATION_JSON)
        );
        mvc.andDo(print());
        mvc.andExpect(status().isNoContent());
        mvc.andExpect(redirectedUrl(null));
        mvc.andExpect(forwardedUrl(null));
    }

    @Test
    void deleteBusinessProfile_Failure() throws Exception {
        // Mock the behavior of businessProfileService
        when(businessProfileService.deleteBusinessProfile(any())).thenThrow(
                new AppException(ErrorCause.BUSINESS_PROFILE_NOT_FOUND.name()));

        ResultActions mvc = this.mockMvc.perform(
                MockMvcRequestBuilders.delete(uri).content(
                        new ObjectMapper().writeValueAsString(new BusinessProfileDeleteDto("Test Name"))
                ).contentType(MediaType.APPLICATION_JSON)
        );
        mvc.andDo(print());
        mvc.andExpect(status().is5xxServerError());
        mvc.andExpect(redirectedUrl(null));
        mvc.andExpect(forwardedUrl(null));
    }

    @Test
    void updateBusinessProfileProducts() throws Exception {
        // Mock the behavior of businessProfileService
        doNothing().when(businessProfileService).updateBusinessProfileProducts(any());

        ResultActions mvc = this.mockMvc.perform(
                MockMvcRequestBuilders.put(uri + "/products").content(
                        new ObjectMapper().writeValueAsString(new BusinessProfileProductsUpdateDto("Name", null, null))
                ).contentType(MediaType.APPLICATION_JSON)
        );
        mvc.andDo(print());
        mvc.andExpect(status().isNoContent());
        mvc.andExpect(redirectedUrl(null));
        mvc.andExpect(forwardedUrl(null));
    }

    @Test
    void updateBusinessProfileProducts_Failure() throws Exception {
        // Mock the behavior of businessProfileService
        doThrow(AppException.class).when(businessProfileService).updateBusinessProfileProducts(any());

        ResultActions mvc = this.mockMvc.perform(
                MockMvcRequestBuilders.put(uri + "/products").content(
                        new ObjectMapper().writeValueAsString(new BusinessProfileProductsUpdateDto("Name", null, null))
                ).contentType(MediaType.APPLICATION_JSON)
        );
        mvc.andDo(print());
        mvc.andExpect(status().is5xxServerError());
        mvc.andExpect(redirectedUrl(null));
        mvc.andExpect(forwardedUrl(null));
    }

}
