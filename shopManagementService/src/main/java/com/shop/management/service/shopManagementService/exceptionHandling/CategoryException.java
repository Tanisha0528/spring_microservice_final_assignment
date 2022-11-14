package com.shop.management.service.shopManagementService.exceptionHandling;

public class CategoryException extends Exception{

    private static final long serialVersionUid=1;
    private String errorMessage;

    public CategoryException() {}

    public CategoryException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }


    public String getErrorMessage()
    {
        return  errorMessage;
    }


}
