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
@CrossOrigin("http://localhost:3000")
public class BusinessProfileController {
    private final BusinessProfileService businessProfileService;

    @Autowired
    public BusinessProfileController(
            BusinessProfileService businessProfileService
    ) {
        this.businessProfileService = businessProfileService;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<BusinessProfileResponse>> createBusinessProfile(
            @RequestBody @Valid BusinessProfileCreateDto createDto) {
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(
                businessProfileService.createBusinessProfile(createDto)
        ));
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<BusinessProfileResponse>> getBusinessProfileByLegalName(
            @RequestParam("legalName") String legalName) {
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(
                businessProfileService.getBusinessProfileByLegalName(legalName)
        ));
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper<BusinessProfileResponse>> updateBusinessProfile(
            @RequestBody BusinessProfileUpdateDto updateDto) {
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(
                businessProfileService.updateBusinessProfile(updateDto)
        ));
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

