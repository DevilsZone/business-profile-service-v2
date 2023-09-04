package com.intuit.interview.businessprofileservice.dtos.request;

import com.intuit.interview.businessprofileservice.dtos.request.common.AddressDto;
import com.intuit.interview.businessprofileservice.dtos.request.common.TaxIdentifiersDto;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessProfileUpdateDto {
    @Nullable
    private String companyName;

    @NotBlank
    private String legalName;

    @Email
    @Nullable
    private String email;

    @URL
    @Nullable
    private String website;

    @Valid
    @Nullable
    private AddressDto businessAddress;

    @Valid
    @Nullable
    private AddressDto legalAddress;

    @Valid
    @Nullable
    private TaxIdentifiersDto taxIdentifiers;

    @NotBlank
    private String updatedBy;
}

