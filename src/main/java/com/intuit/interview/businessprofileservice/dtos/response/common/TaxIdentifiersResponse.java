package com.intuit.interview.businessprofileservice.dtos.response.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class TaxIdentifiersResponse {
    private String pan;

    private String ein;
}
