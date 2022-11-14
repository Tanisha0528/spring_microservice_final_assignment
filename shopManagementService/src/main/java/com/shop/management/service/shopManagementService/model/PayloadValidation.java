package com.shop.management.service.shopManagementService.model;

public class PayloadValidation {

    public static boolean createdPayloadValidationCategory(Category category)
    {
        if(category.getId()>0) {
            return false;
        }
        return true;
    } public static boolean createdPayloadValidationProduct(Product product)
    {
        if(product.getPid() >0) {
            return false;
        }
        return true;
    }
}
