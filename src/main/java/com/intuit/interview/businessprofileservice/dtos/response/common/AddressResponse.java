package com.intuit.interview.businessprofileservice.dtos.response.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class AddressResponse {
    private String line1;

    private String line2;

    private String city;

    private String state;

    private String zip;

    private String country;
}
