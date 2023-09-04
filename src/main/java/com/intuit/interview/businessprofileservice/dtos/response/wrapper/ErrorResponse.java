package com.intuit.interview.businessprofileservice.dtos.response.wrapper;

import com.intuit.interview.businessprofileservice.enums.ErrorCode;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse implements Serializable {
    private String errorCode;

    private String message;

    private String detail;

    private String help;

    public static ErrorResponse from(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .errorCode(errorCode.getCode())
                .message(errorCode.getMessage())
                .detail(errorCode.getDetail())
                .help(errorCode.getHelp())
                .build();
    }
}
