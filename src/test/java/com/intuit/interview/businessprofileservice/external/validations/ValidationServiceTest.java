package com.intuit.interview.businessprofileservice.external.validations;

import com.intuit.interview.businessprofileservice.models.BusinessProfile;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class ValidationServiceTest {

    private static MockWebServer mockWebServer;

    private final List<ProductValidationService> allServices;

    @Autowired
    public ValidationServiceTest(ApplicationContext context) {
        this.allServices = new ArrayList<>(context.getBeansOfType(ProductValidationService.class).values());
    }

    @BeforeEach
    public void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        // Replace WebClient in each service with the one for MockWebServer
        for (ProductValidationService service : allServices) {
            ReflectionTestUtils.setField(
                    service,
                    "webClient",
                    WebClient.builder().baseUrl(mockWebServer.url("/").toString()).build()
            );
        }
    }

    @AfterEach
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    private Stream<ProductValidationService> serviceImplementations() {
        return allServices.stream();
    }

    @ParameterizedTest
    @MethodSource("serviceImplementations")
    void testValidateBusinessProfileForCreate_Success(ProductValidationService service) {
        mockWebServer.enqueue(new MockResponse().setResponseCode(200));

        BusinessProfile businessProfile = new BusinessProfile();
        service.validateBusinessProfileForCreate(businessProfile).block();

        assertEquals(1, mockWebServer.getRequestCount());
    }

    @ParameterizedTest
    @MethodSource("serviceImplementations")
    void testValidateBusinessProfileForCreate_Failure(ProductValidationService service) {
        mockWebServer.enqueue(new MockResponse().setResponseCode(500));

        BusinessProfile businessProfile = new BusinessProfile();
        assertThrows(Exception.class, () ->
                service.validateBusinessProfileForCreate(businessProfile).block()
        );

        assertEquals(1, mockWebServer.getRequestCount());
    }

    @ParameterizedTest
    @MethodSource("serviceImplementations")
    void testValidateBusinessProfileForUpdate_Success(ProductValidationService service) {
        mockWebServer.enqueue(new MockResponse().setResponseCode(200));

        BusinessProfile businessProfile = new BusinessProfile();
        service.validateBusinessProfileForUpdate(businessProfile).block();

        assertEquals(1, mockWebServer.getRequestCount());
    }

    @ParameterizedTest
    @MethodSource("serviceImplementations")
    void testValidateBusinessProfileForUpdate_Failure(ProductValidationService service) {
        mockWebServer.enqueue(new MockResponse().setResponseCode(500));

        BusinessProfile businessProfile = new BusinessProfile();
        assertThrows(Exception.class, () ->
                service.validateBusinessProfileForUpdate(businessProfile).block()
        );

        assertEquals(1, mockWebServer.getRequestCount());
    }

    @ParameterizedTest
    @MethodSource("serviceImplementations")
    void testValidateBusinessProfileForDelete_Success(ProductValidationService service) {
        mockWebServer.enqueue(new MockResponse().setResponseCode(200));

        BusinessProfile businessProfile = new BusinessProfile();
        service.validateBusinessProfileForDelete(businessProfile).block();

        assertEquals(1, mockWebServer.getRequestCount());
    }

    @ParameterizedTest
    @MethodSource("serviceImplementations")
    void testValidateBusinessProfileForDelete_Failure(ProductValidationService service) {
        mockWebServer.enqueue(new MockResponse().setResponseCode(500));

        BusinessProfile businessProfile = new BusinessProfile();
        assertThrows(Exception.class, () ->
                service.validateBusinessProfileForDelete(businessProfile).block()
        );

        assertEquals(1, mockWebServer.getRequestCount());
    }
}

