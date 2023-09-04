package com.intuit.interview.businessprofileservice.utils;

public final class MessageConstants {
    public static final String GLOBAL_EXCEPTION_HANDLER_CAPTURE_MESSAGE =
            "Exception of type `%s` captured in the GlobalExceptionHandler";

    public static final String VALIDATION_ERROR_MESSAGE_FORMAT = "%s: %s";

    public static final String DUPLICATE_VALUE_FOUND_IN_ENUM = "Duplicate value `%s` found in enum `%s`";

    public static final String THIS_METHOD_MUST_BE_INVOKED_WITH_AN_ENUM_CLASS =
            "This method must be invoked with an Enum class";

    public static final String MUST_BE_NULL_OR_MUST_NOT_BE_BLANK = "must be null or must not be blank";

    public static final String ENUM_INVALID_CAST_MESSAGE = "%s: must be among %s";

    public static final String REQUIRED_REQUEST_BODY_IS_MISSING = "Required request body is missing";

    public static final String UNSUPPORTED_MEDIA_TYPE_MESSAGE =
            "MediaType `%s` is not supported. MediaType must be among %s";

    private MessageConstants() {
    }
}
