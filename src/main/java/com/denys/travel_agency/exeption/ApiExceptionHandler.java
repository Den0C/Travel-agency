package com.denys.travel_agency.exeption;


import com.denys.travel_agency.dto.MyApiResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler {

    private static final Logger logger = LogManager.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyApiResponse<Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        List<String> errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        String errorMessage = String.join(", ", errorMessages);

        logger.error("Validation error: {}", errorMessage);

        return new ResponseEntity<>(MyApiResponse.builder()
                .statusCode(StatusCodes.INVALID_DATA.name())
                .statusMessage(errorMessage)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MyApiResponse<Object>> handleValidationExceptions(
            EntityNotFoundException ex) {

        logger.error("Entity not found: {}", ex.getMessage());

        return new ResponseEntity<>(MyApiResponse.builder()
                .statusCode(StatusCodes.ENTITY_NOT_FOUND.name())
                .statusMessage(ex.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MyApiResponse<Object>> handleIllegalArgumentException(
            IllegalArgumentException ex) {

        logger.error("Illegal argument: {}", ex.getMessage());

        return new ResponseEntity<>(MyApiResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.name())
                .statusMessage(ex.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }
}

