package com.intuit.interview.businessprofileservice.controllers;

import com.intuit.interview.businessprofileservice.builders.BusinessProfileBuilderForTests;
import com.intuit.interview.businessprofileservice.configurations.AppConfiguration;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileCreateDto;
import com.intuit.interview.businessprofileservice.dtos.response.BusinessProfileResponse;
import com.intuit.interview.businessprofileservice.services.BusinessProfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebMvcTest(BusinessProfileController.class)
class BusinessProfileControllerTest {

    @Autowired
    private AppConfiguration appConfiguration;

    @MockBean
    private BusinessProfileService businessProfileService;

    @Autowired
    private MockMvc mockMvc;

//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(businessProfileController).build();
//    }
//
//    @AfterEach
//    public void close() {
//        clearAllCaches();
//    }

    @Test
    public void createBusinessProfile() throws Exception {
        BusinessProfileCreateDto dto = BusinessProfileBuilderForTests.createBusinessProfileDto();
        BusinessProfileResponse response = BusinessProfileBuilderForTests.createBusinessProfileResponse();
        when(businessProfileService.createBusinessProfile(any())).thenReturn(response);

        mockMvc.perform(post("/business-profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(appConfiguration.getJsonMapper().writeValueAsString(dto)))
                // to JSON string
                .andExpect(status().isOk());
        verify(businessProfileService).createBusinessProfile(dto);

//        verify(businessProfileService, times(1)).createBusinessProfile(any(BusinessProfileCreateDto.class));
    }
}

