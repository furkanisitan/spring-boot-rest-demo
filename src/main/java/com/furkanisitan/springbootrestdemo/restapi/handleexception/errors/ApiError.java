package com.furkanisitan.springbootrestdemo.restapi.handleexception.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    private HttpStatus status;
    private String message;
    private String debugMessage;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Setter(AccessLevel.NONE)

    private LocalDateTime timestamp;
    @Setter(AccessLevel.NONE)

    private List<ApiSubError> subErrors;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    public void addFluentValidationError(String entityName, Collection<br.com.fluentvalidator.context.Error> errors) {
        for (var error : errors)
            addSubError(new ApiValidationError(entityName, error.getField(), error.getAttemptedValue(), error.getMessage()));
    }

    private void addSubError(ApiSubError subError) {
        if (subErrors == null)
            subErrors = new ArrayList<>();
        subErrors.add(subError);
    }
}
