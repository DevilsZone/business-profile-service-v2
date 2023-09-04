package com.intuit.interview.businessprofileservice.repositories;

import com.intuit.interview.businessprofileservice.models.BusinessProfile;
import jakarta.annotation.Nullable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessProfileRepository extends MongoRepository<BusinessProfile, String> {
    @Nullable BusinessProfile getBusinessProfileByLegalName(String companyName);

    Boolean deleteBusinessProfileByLegalName(String companyName);
}
