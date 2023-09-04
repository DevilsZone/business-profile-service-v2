package com.intuit.interview.businessprofileservice.models;

import com.intuit.interview.businessprofileservice.models.common.Address;
import com.intuit.interview.businessprofileservice.models.common.TaxIdentifiers;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Document
@Builder
public class BusinessProfile extends BaseEntity {
    @Id
    private String _id;

    private String companyName;

    // Given that these are small businesses, it's un likely that legalName will change. Hence, taking this as
    // partition key and all queries of get will be done on legalName only then
    @Indexed(unique = true)
    private String legalName;

    private String email;

    private String website;

    private Address businessAddress;

    private Address legalAddress;

    private TaxIdentifiers taxIdentifiers;
}

