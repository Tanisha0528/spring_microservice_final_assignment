package com.shop.management.service.shopManagementService.controller;

import com.shop.management.service.shopManagementService.exceptionHandling.CategoryException;
import com.shop.management.service.shopManagementService.model.Category;
import com.shop.management.service.shopManagementService.model.PayloadValidation;
import com.shop.management.service.shopManagementService.model.Response;
import com.shop.management.service.shopManagementService.repository.CategoryDAO;
import com.shop.management.service.shopManagementService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("category")
public class CategoryController {
    private CategoryService service;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    public CategoryController(CategoryService service){
        this.service = service;
    }


    @PostMapping(value = "/add-category")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) throws CategoryException {


        if(!PayloadValidation.createdPayloadValidationCategory(category)) {
            throw new CategoryException("PAYLOAD MALFORMED. ID MUST NOT BE DEFINED");
        }

        return new ResponseEntity<>(service.addCategory(category), HttpStatus.OK);

    }

    @PostMapping(value = "/update-category/{id}")
    public String updateCategoryById(@PathVariable("id") int id,@RequestBody Category category) {
        return service.updateCategoryById(id,category);
    }
    @DeleteMapping(value = "/delete-category/{id}")
    public ResponseEntity<Response>  deleteCategoryById(@PathVariable("id")int id)throws CategoryException {

        Category cat=service.getCategoryById(id);
        if(cat==null|| cat.getId()<=0){
            throw new CategoryException("category doesn't exist");
        }
        service.deleteCategoryById(id);
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "category deleted sucessfully"),HttpStatus.OK);
    }
    @GetMapping(value = "/")
    public ResponseEntity<List<Category>> getAllCategories()
    {
        return new ResponseEntity<>(categoryDAO.getAllCategories(), HttpStatus.OK);
    }


    @PostMapping(value = "/")
    public List<Category> getByFieldCategories(@RequestBody Category category)
    {
        if(category.getId()>0)
            return categoryDAO.getAllCategoryById(category.getId());
        if(category.getName()!=null)
            return categoryDAO.getAllCategoryByName(category.getName());
        if(category.getDescription()!=null)
            return categoryDAO.getAllCategoryByDescription(category.getDescription());

        return null;

    }

}
