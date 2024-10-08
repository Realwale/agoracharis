package com.agoracharis.productservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


import java.io.Serial;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class APIErrorResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 6877490277661133451L;

    private HttpStatus status;
    private Integer error;
    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private APIErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    APIErrorResponse(HttpStatus status, String message, Integer error) {
        this();
        this.status = status;
        this.message = message;
        this.error = error;
    }
}