package com.forestsoftware.sendmeerrandserverjava.Exceptions;

import com.forestsoftware.sendmeerrandserverjava.Exceptions.builder.ApiError;
import com.forestsoftware.sendmeerrandserverjava.Exceptions.builder.InvalidResourceException;
import com.forestsoftware.sendmeerrandserverjava.Exceptions.builder.ResponseEntityBuilder;
import com.forestsoftware.sendmeerrandserverjava.models.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
//@ResponseStatus
public class ResponseEntityExceptionHandler extends org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseEntityExceptionHandler.class);

//    @ExceptionHandler(DepartmentNotFoundException.class)
//    public ResponseEntity<ErrorMessage> departmentNotFoundException(DepartmentNotFoundException e, WebRequest webRequest){
//        System.out.println("++++++++++++++++++");
//        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
//
//    }

//    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Request body are required")
//    @ExceptionHandler(DepartmentNotFoundException.class)
//    public ResponseEntity<?> handleHttpMessageNotReadableException(){
//        LOGGER.error("==============Log it");
//    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOGGER.error("==============Log it");
        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Malformed JSON request" ,
                details);

        return ResponseEntityBuilder.build(err);
    }


    @ExceptionHandler(InvalidResourceException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
            InvalidResourceException ex) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Resource Not Found" ,
                details);

        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(
            Exception ex,
            WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getLocalizedMessage());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Error occurred" ,
                details);

        return ResponseEntityBuilder.build(err);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> details = new ArrayList<String>();
        details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getObjectName()+ " : " +error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Validation Errors" ,
                details);

        return ResponseEntityBuilder.build(err);
    }

}
