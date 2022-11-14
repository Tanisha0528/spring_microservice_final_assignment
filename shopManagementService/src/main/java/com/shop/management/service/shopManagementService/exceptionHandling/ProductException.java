package com.shop.management.service.shopManagementService.exceptionHandling;

public class ProductException extends Exception{

    private static final long serialVersionUid=1;
    private String errorMessage;

    public ProductException() {}

    public ProductException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }


    public String getErrorMessage()
    {
        return  errorMessage;
    }


}
