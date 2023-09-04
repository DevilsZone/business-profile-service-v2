package com.intuit.interview.businessprofileservice.enums;

import com.intuit.interview.businessprofileservice.utils.Constants;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("001", "Internal Server Error", null, null),
    INPUT_VALIDATION_ERROR("002", "Input Validation Error", null, null),
    BAD_REQUEST_ERROR("003", "Bad Request", null, null),
    NOT_FOUND_ERROR("004", "Response not found", null, null),
    ;

    private final String code;

    private final String message;

    private final String detail;

    private final String help;

    ErrorCode(String code, String message, String detail, String help) {
        this.code = Constants.ERROR_CODE_PREFIX + code;
        this.message = message;
        this.detail = detail;
        this.help = help;
    }
}
