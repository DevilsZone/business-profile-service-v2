package com.intuit.interview.businessprofileservice.dtos.response.wrapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.intuit.interview.businessprofileservice.enums.ResponseStatus;
import com.intuit.interview.businessprofileservice.utils.Constants;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class MetaResponse {
    private ResponseStatus status;

    @JsonFormat(pattern = Constants.RESPONSE_TIMESTAMP_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDateTime timestamp;
}
