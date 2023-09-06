package com.intuit.interview.businessprofileservice.models.common;

import com.intuit.interview.businessprofileservice.enums.Product;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BusinessProfileProduct {
    private Product product;
}
