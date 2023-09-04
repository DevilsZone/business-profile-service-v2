package com.intuit.interview.businessprofileservice.models.common;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Address {
    private String line1;

    private String line2;

    private String city;

    private String state;

    private String zip;

    private String country;
}
