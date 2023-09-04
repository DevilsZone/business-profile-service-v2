package com.intuit.interview.businessprofileservice.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseEntity {
    private Long version;

    private Long createdAt;

    private Long updatedAt;

    private String createdBy;

    private String updatedBy;
}
