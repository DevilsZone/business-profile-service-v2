package com.intuit.interview.businessprofileservice.dtos.request;


import com.intuit.interview.businessprofileservice.dtos.request.common.AddressDto;
import com.intuit.interview.businessprofileservice.dtos.request.common.TaxIdentifiersDto;
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
public class BusinessProfileCreateDto {
    @NotBlank
    private String companyName;

    @NotBlank
    private String legalName;

    @NotBlank
    @Email
    private String email;

    @URL
    private String website;

    @Valid
    private AddressDto businessAddress;

    @Valid
    private AddressDto legalAddress;

    @Valid
    private TaxIdentifiersDto taxIdentifiers;

    @NotBlank
    private String createdBy;
}

