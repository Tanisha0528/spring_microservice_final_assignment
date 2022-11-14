package com.shop.management.service.shopManagementService.controller;

import com.shop.management.service.shopManagementService.exceptionHandling.CategoryException;
import com.shop.management.service.shopManagementService.exceptionHandling.ProductException;
import com.shop.management.service.shopManagementService.model.Category;
import com.shop.management.service.shopManagementService.model.PayloadValidation;
import com.shop.management.service.shopManagementService.model.Product;
import com.shop.management.service.shopManagementService.model.Response;
import com.shop.management.service.shopManagementService.repository.CategoryRepository;
import com.shop.management.service.shopManagementService.repository.ProductDAO;
import com.shop.management.service.shopManagementService.service.CategoryService;
import com.shop.management.service.shopManagementService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductService service;
    @Autowired
    private CategoryService service1;

    @Autowired
    private ProductDAO productDAO;




    @PostMapping(value = "/add-product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) throws ProductException
    {
        if(!PayloadValidation.createdPayloadValidationProduct(product)) {
            throw new ProductException("PAYLOAD MALFORMED. ID MUST NOT BE DEFINED");
        }

        return new ResponseEntity<>(service.addProduct(product), HttpStatus.OK);

    }
    @PutMapping(value ="/product-with-category/{pid}/{cid}")

    public String linkProductWithCategory(@PathVariable("pid") int pid,@PathVariable("cid") int cid){
        Category category =service1.findByCategoryId(cid);
        if(category!=null) {
             Category category1=new Category(cid,category.getName(),category.getDescription());
            return service.linkProductWithCategory(pid, category1);
        }
        else
            return "no such category exist";
    }

    @PutMapping(value = "/update-product/{pid}")
    public String updateProductById(@PathVariable("pid") int pid,@RequestBody Product product) {
        return service.updateProductById(pid,product);
    }
    @DeleteMapping(value = "/delete-product/{pid}")
    public ResponseEntity<Response> deleteProductById(@PathVariable("pid")int pid) throws ProductException {

        Product p=service.getProductById(pid);
        if(p==null|| p.getPid()<=0){
            throw new ProductException("product doesn't exist");
        }
        service.deleteProductById(pid);
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "product deleted sucessfully"),HttpStatus.OK);
    }
    @GetMapping(value = "/")
    public  ResponseEntity<List<Product>> getAllProducts()
    {
        return new ResponseEntity<>(productDAO.getAllProducts(), HttpStatus.OK);
    }


    @PostMapping(value = "/")
    public List<Product> getByFieldProducts(@RequestBody Product product)
    {
        if(product.getPid()>0)
            return productDAO.getAllProductById(product.getPid());
        if(product.getName()!=null)
            return productDAO.getAllProductByName(product.getName());
        if(product.getDescription()!=null)
            return productDAO.getAllProductByDescription(product.getDescription());
        if(product.getPrice()!=null)
            return productDAO.getAllProductByPrice(product.getPrice());

        return null;

    }

}
