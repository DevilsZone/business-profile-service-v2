package com.intuit.interview.businessprofileservice.controllers;

import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileCreateDto;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileDeleteDto;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileUpdateDto;
import com.intuit.interview.businessprofileservice.dtos.response.BusinessProfileResponse;
import com.intuit.interview.businessprofileservice.dtos.response.wrapper.ResponseWrapper;
import com.intuit.interview.businessprofileservice.services.BusinessProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/business-profile")
public class BusinessProfileController {

    @Autowired
    private BusinessProfileService businessProfileService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<BusinessProfileResponse>> createBusinessProfile(
            @RequestBody @Valid BusinessProfileCreateDto createDto) {
        BusinessProfileResponse response = businessProfileService.createBusinessProfile(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.success(response));
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<BusinessProfileResponse>> getBusinessProfileByCompanyName(
            @RequestParam("legalName") String legalName) {
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(
                businessProfileService.getBusinessProfileByLegalName(legalName)
        ));
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper<BusinessProfileResponse>> updateBusinessProfile(
            @RequestBody BusinessProfileUpdateDto updateDto) {
        BusinessProfileResponse response = businessProfileService.updateBusinessProfile(updateDto);
        if (response != null) {
            return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(response));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping
    public ResponseEntity<ResponseWrapper<Void>> deleteBusinessProfile(
            @RequestBody BusinessProfileDeleteDto deleteDto) {
        boolean deleted = businessProfileService.deleteBusinessProfile(deleteDto);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

