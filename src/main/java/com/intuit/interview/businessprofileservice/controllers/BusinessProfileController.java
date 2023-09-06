package com.intuit.interview.businessprofileservice.controllers;

import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileCreateDto;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileDeleteDto;
import com.intuit.interview.businessprofileservice.dtos.request.BusinessProfileUpdateDto;
import com.intuit.interview.businessprofileservice.dtos.response.BusinessProfileResponse;
import com.intuit.interview.businessprofileservice.dtos.response.wrapper.ResponseWrapper;
import com.intuit.interview.businessprofileservice.services.BusinessProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Create new business profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully create new Profile"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<ResponseWrapper<BusinessProfileResponse>> createBusinessProfile(
            @RequestBody @Valid BusinessProfileCreateDto createDto) {
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(
                businessProfileService.createBusinessProfile(createDto)
        ));
    }

    @GetMapping
    @Operation(summary = "Get business profile against a legal name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched Profile"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<ResponseWrapper<BusinessProfileResponse>> getBusinessProfileByLegalName(
            @RequestParam("legalName") String legalName) {
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(
                businessProfileService.getBusinessProfileByLegalName(legalName)
        ));
    }

    @PutMapping
    @Operation(summary = "Update business profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully update Profile"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<ResponseWrapper<BusinessProfileResponse>> updateBusinessProfile(
            @RequestBody BusinessProfileUpdateDto updateDto) {
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(
                businessProfileService.updateBusinessProfile(updateDto)
        ));
    }

    @DeleteMapping
    @Operation(summary = "Remove a business profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully delete Profile"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
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

