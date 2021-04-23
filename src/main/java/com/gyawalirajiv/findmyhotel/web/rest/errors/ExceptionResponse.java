package com.gyawalirajiv.findmyhotel.web.rest.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    String message;
    Instant instant;
}
