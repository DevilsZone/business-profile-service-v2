package com.intuit.interview.businessprofileservice.configurations;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.intuit.interview.validation")
@Getter
@Setter
@NoArgsConstructor
public class ValidationConfiguration {
    // Quick Book Configuration
    private String quickbookBaseUrl;

    private String quickbookCreateUri;

    private String quickbookUpdateUri;

    private String quickbookDeleteUri;
    // Quick Book Payroll Configuration
}
