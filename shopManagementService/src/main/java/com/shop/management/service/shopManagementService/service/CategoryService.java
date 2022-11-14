package com.shop.management.service.shopManagementService.service;

import com.shop.management.service.shopManagementService.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shop.management.service.shopManagementService.model.Category;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;



    public Category addCategory(Category category){


        Category savedCategory = categoryRepository.save(category);
        return savedCategory;
    }
    public String updateCategoryById(int id, Category category)
    {
        Optional<Category> op=categoryRepository.findById(id);
       if(op.isPresent()) {

           Category p=op.get();

           if(category.getName() !=null)
           {

                   p.setName(category.getName());
           }
           if(category.getDescription()!=null)
           {

                   p.setDescription(category.getDescription());
           }

           //deleteCategoryById(id);

           categoryRepository.save(p);
           return "updated successfully";


        }
       return "category with id: "+id+" doesn't exist";

    }
    public String deleteCategoryById(int id)
    {
        Optional<Category> op=categoryRepository.findById(id);
       if(op.isPresent()) {

           Category p=op.get();


           categoryRepository.delete(p);

           return "category with id: "+id+" deleted successfully";


        }
       return "category with id: "+id+" doesn't exist";

    }
    public Category findByCategoryId(int id)
    {
        Optional<Category>op= categoryRepository.findById(id);
        Category p=null;
        if(op.isPresent()) {
            p=op.get();
        }
        else
        {

            return null;
        }
        return p;
    }
    public Category getCategoryById(int id) {
        Optional<Category> cat=categoryRepository.findById(id);
        if(cat.isPresent())
            return cat.get();
        return null;
    }
}
