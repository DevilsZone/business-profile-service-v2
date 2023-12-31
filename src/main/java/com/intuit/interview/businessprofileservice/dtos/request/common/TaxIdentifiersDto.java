package com.intuit.interview.businessprofileservice.dtos.request.common;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxIdentifiersDto {

    @NotBlank(message = "PAN (Permanent Account Number) cannot be blank")
    private String pan;

    private String ein;
}

