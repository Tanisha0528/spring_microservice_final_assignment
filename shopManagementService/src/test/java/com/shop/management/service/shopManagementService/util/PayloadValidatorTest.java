package com.shop.management.service.shopManagementService.util;

import com.shop.management.service.shopManagementService.model.Category;
import com.shop.management.service.shopManagementService.model.Product;

import com.shop.management.service.shopManagementService.model.PayloadValidation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PayloadValidatorTest {

    @Test
    public void validatePayloadCategory()
    {
        Category cat=new Category(1,"soap","nivea,mamaearth");
        assertEquals(false, PayloadValidation.createdPayloadValidationCategory(cat));

        Product p=new Product(1,"soap1","50","sandal wood",null);
        assertEquals(false, PayloadValidation.createdPayloadValidationProduct(p));


    }  @Test
    public void validateInvalidPayload()
    {
        Category cat=new Category(0,"soap","nivea,mamaearth");
        assertEquals(true, PayloadValidation.createdPayloadValidationCategory(cat));
        Product p=new Product(0,"soap1","50","sandal wood",null);
        assertEquals(true, PayloadValidation.createdPayloadValidationProduct(p));




    }
}
