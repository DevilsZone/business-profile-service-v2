package com.intuit.interview.businessprofileservice.dtos.request;


import com.intuit.interview.businessprofileservice.dtos.request.common.AddressDto;
import com.intuit.interview.businessprofileservice.dtos.request.common.TaxIdentifiersDto;
import com.intuit.interview.businessprofileservice.enums.Product;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Pattern(regexp = "^(http|https)://.*$", message = "Invalid URL format")
    @Nullable
    private String website;

    @Valid
    private AddressDto businessAddress;

    @Valid
    private AddressDto legalAddress;

    @Valid
    private TaxIdentifiersDto taxIdentifiers;

    @NotBlank
    private String createdBy;

    @Nullable
    private Product product;
}

