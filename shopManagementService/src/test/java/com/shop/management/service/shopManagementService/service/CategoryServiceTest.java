package com.shop.management.service.shopManagementService.service;


import com.shop.management.service.shopManagementService.model.Category;
import com.shop.management.service.shopManagementService.repository.CategoryDAO;
import com.shop.management.service.shopManagementService.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryServiceTest {

    //dummy data
    @Mock
    private CategoryRepository repository;

    //dummy service
    @InjectMocks
    private CategoryService service;

    @InjectMocks
    private CategoryDAO categoryDAO;

    //before each test case ,mocked data is made ready
    @Before
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllCategories()
    {
        List<Category> catList=new ArrayList<>();
        catList.add(new Category(1,"soap","nivea,mamaearth"));
        catList.add(new Category(2,"shampoo","mamaearth,loreal,sunsilk"));

        when(repository.findAll()).thenReturn(catList);

        List<Category> catResult=categoryDAO.getAllCategories();
        assertEquals(2,catResult.size());
    }

    @Test
    public void  getAllCategoryById() {
        Category category = new Category(1,"soap","nivea,mamaearth");
        when(repository.findById(1)).thenReturn(Optional.of(category));

        Category catResult = service.getCategoryById(1);

        assertEquals(1, catResult.getId());
        assertEquals("soap", catResult.getName());
        assertEquals("nivea,mamaearth", catResult.getDescription());
    }
    @Test
    public void  addCategory() {
        Category category = new Category(1,"soap","nivea,mamaearth");
        when(repository.save(category)).thenReturn(category);

        Category catResult = service.addCategory(category);

        assertEquals(1, catResult.getId());
        assertEquals("soap", catResult.getName());
        assertEquals("nivea,mamaearth", catResult.getDescription());
    }
    @Test
    public void  deleteCategoryById() {
        Category category = new Category(1,"soap","nivea,mamaearth");
       service.deleteCategoryById(1);
       verify(repository,times(1)).deleteById(category.getId());


    }

    @Test
    public void  updateCategory() {
        Category category = new Category(1,"soap","nivea,mamaearth");
        when(repository.save(category)).thenReturn(category);


        Category category1= new Category(1,"soap","nivea,mamaearth,khadi");

        if(category.getName()!=category1.getName())
            category.setName(category1.getName());
        if(category.getDescription()!=category1.getDescription())
            category.setDescription(category1.getDescription());
        when(repository.save(category)).thenReturn(category);
        Category catResult = service.addCategory(category);

        assertEquals(1, catResult.getId());
        assertEquals("soap", catResult.getName());
        assertEquals("nivea,mamaearth,khadi", catResult.getDescription());
    }




}
