package com.intuit.interview.businessprofileservice.repositories;

import com.intuit.interview.businessprofileservice.TestBusinessProfileServiceApplication;
import com.intuit.interview.businessprofileservice.models.BusinessProfile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@Import(TestBusinessProfileServiceApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BusinessProfileRepositoryTest {

    @Autowired
    private BusinessProfileRepository repository;

    @Autowired
    private MongoDBContainer mongoDbContainer;

    @BeforeEach
    public void setUp() {
        String containerIpAddress = mongoDbContainer.getContainerIpAddress();
        Integer mappedPort = mongoDbContainer.getMappedPort(27017);
        System.setProperty("spring.data.mongodb.uri", "mongodb://" + containerIpAddress + ":" + mappedPort);
    }

    @AfterEach
    public void teardown() {
        repository.deleteAll();
    }

    @Test
    void testSaveBusinessProfile() {
        BusinessProfile profile = new BusinessProfile();
        profile.setLegalName("Company A");

        BusinessProfile savedProfile = repository.save(profile);
        assertNotNull(savedProfile);
        assertNotNull(savedProfile.get_id());
    }

    @Test
    void testGetBusinessProfileByLegalName() {
        BusinessProfile profile = new BusinessProfile();
        profile.setLegalName("Company B");
        // Set other fields...

        repository.save(profile);

        BusinessProfile foundProfile = repository.getBusinessProfileByLegalName("Company B");
        assertNotNull(foundProfile);
        assertEquals(profile.getLegalName(), foundProfile.getLegalName());
    }

    @Test
    void testDeleteBusinessProfileByLegalName() {
        BusinessProfile profile = new BusinessProfile();
        profile.setLegalName("Company C");
        // Set other fields...

        repository.save(profile);

        assertEquals(1, repository.deleteBusinessProfileByLegalName("Company C"));
        assertNull(repository.getBusinessProfileByLegalName("Company C"));
    }

}
