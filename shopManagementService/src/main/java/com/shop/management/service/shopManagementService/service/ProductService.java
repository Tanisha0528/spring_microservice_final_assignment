package com.shop.management.service.shopManagementService.service;

import com.shop.management.service.shopManagementService.model.Category;
import com.shop.management.service.shopManagementService.model.Product;
import com.shop.management.service.shopManagementService.repository.CategoryRepository;
import com.shop.management.service.shopManagementService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product){


        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    public String linkProductWithCategory(int pid,Category category){

        Optional<Product>op=productRepository.findById(pid);
        if(op.isPresent()) {


            Product p=op.get();
            p.setCategory(category);
            productRepository.save(p);
            return "linked successfully";
            }

        return "product with the id:"+pid+" not found";
    }

    public String updateProductById(int pid, Product product)
    {
        Optional<Product> op=productRepository.findById(pid);
        if(op.isPresent()) {

            Product p=op.get();

            if(product.getName()!=null)
            {

                    p.setName(product.getName());
            }
            if(product.getPrice()!=null)
            {

                    p.setPrice(product.getPrice());
            }
            if(product.getDescription()!=null)
            {

                    p.setDescription(product.getDescription());
            }
            if(product.getCategory()!=null) {
                Category c=product.getCategory();
                Category c1=p.getCategory();
                if(c.getName()!=null && c.getDescription()!=null )
                {
                  p.setCategory(c);
                }

                else if(c.getName()!=null)
                {
                    c1.setName(c.getName());
                    p.setCategory(c1);
                }
                else {
                    c1.setDescription(c.getDescription());
                    p.setCategory(c1);
                }
            }


            // productRepository.delete(p);
            productRepository.save(p);
            return "updated successfully";


        }
        return "product with id: "+pid+" doesn't exist";

    }

    public String deleteProductById(int pid)
    {
        Optional<Product> op=productRepository.findById(pid);
        if(op.isPresent()) {

            Product p=op.get();


            productRepository.delete(p);

            return "product with id: "+pid+" deleted successfully";


        }
        return "product with id: "+pid+" doesn't exist";

    }

    public Product getProductById(int pid) {
        Optional<Product> p=productRepository.findById(pid);
        if(p.isPresent())
            return p.get();
        return null;
    }
}
