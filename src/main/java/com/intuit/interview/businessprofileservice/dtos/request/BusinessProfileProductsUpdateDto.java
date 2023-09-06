package com.intuit.interview.businessprofileservice.dtos.request;

import com.intuit.interview.businessprofileservice.enums.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessProfileProductsUpdateDto {
    @NotBlank
    private String legalName;

    private List<Product> productsToBeAdded;

    private List<Product> productsToBeRemoved;
}
