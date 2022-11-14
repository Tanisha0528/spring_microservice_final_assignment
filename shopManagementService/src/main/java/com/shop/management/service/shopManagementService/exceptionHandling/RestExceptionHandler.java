package com.shop.management.service.shopManagementService.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<ErrorResponse> exceptionCategoryHandler(Exception e)
    {
        ErrorResponse err=new ErrorResponse();
        err.setErrorCode(HttpStatus.NOT_FOUND.value());
        err.setMessage(e.getMessage());
        return  new ResponseEntity<ErrorResponse>(err,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ErrorResponse> exceptionProductHandler(Exception e)
    {
        ErrorResponse err=new ErrorResponse();
        err.setErrorCode(HttpStatus.NOT_FOUND.value());
        err.setMessage(e.getMessage());
        return  new ResponseEntity<ErrorResponse>(err,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception e){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage("THE REQUEST CANNOT BE PLACED DUE TO MALFUNCTION SYNTAX");

        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
