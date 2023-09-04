package com.intuit.interview.businessprofileservice.models.common;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class TaxIdentifiers {
    private String pan;  // Permanent Account Number

    private String ein;  // Employer Identification Number
}
