package com.shop.management.service.shopManagementService.service;


import com.shop.management.service.shopManagementService.model.Category;
import com.shop.management.service.shopManagementService.model.Product;
import com.shop.management.service.shopManagementService.repository.CategoryDAO;
import com.shop.management.service.shopManagementService.repository.CategoryRepository;
import com.shop.management.service.shopManagementService.repository.ProductDAO;
import com.shop.management.service.shopManagementService.repository.ProductRepository;
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
public class ProductServiceTest {

    //dummy data
    @Mock
    private ProductRepository repository;

    //dummy service
    @InjectMocks
    private ProductService service;

    @InjectMocks
    private ProductDAO productDAO;

    //before each test case ,mocked data is made ready
    @Before
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllProducts()
    {
        List<Product> proList=new ArrayList<>();
        proList.add(new Product(1,"soap1","50","sandal wood",null));
        proList.add(new Product(2,"shampoo1","500","for dry hair ",null));

        when(repository.findAll()).thenReturn(proList);

        List<Product> proResult=productDAO.getAllProducts();
        assertEquals(2,proResult.size());
    }

    @Test
    public void  getAllProductById() {
        Product product = new Product(1,"soap1","50","sandal wood",null);
        when(repository.findById(1)).thenReturn(Optional.of(product));

        Product proResult = service.getProductById(1);

        assertEquals(1, proResult.getPid());
        assertEquals("soap1", proResult.getName());
        assertEquals("50", proResult.getPrice());
        assertEquals("sandal wood", proResult.getDescription());
    }
    @Test
    public void  addProduct() {
        Product product = new Product(1,"soap1","50","sandal wood",null);
        when(repository.save(product)).thenReturn(product);

        Product proResult = service.addProduct(product);

        assertEquals(1, proResult.getPid());
        assertEquals("soap1", proResult.getName());
        assertEquals("50", proResult.getPrice());
        assertEquals("sandal wood", proResult.getDescription());
    }
    @Test
    public void  deleteCategoryById() {
        Product product = new Product(1,"soap1","50","sandal wood",null);
        service.deleteProductById(1);
       verify(repository,times(1)).deleteById(product.getPid());


    }

    @Test
    public void  updateCategory() {
        Product product = new Product(1,"soap1","50","sandal wood",null);
        when(repository.save(product)).thenReturn(product);


        Product product1 = new Product(1,"soap1","50","sandal wood and milk",null);


        if(product.getName()!=product1.getName())
            product.setName(product1.getName());
        if(product.getDescription()!=product1.getDescription())
            product.setDescription(product1.getDescription());
        if(product.getPrice()!=product1.getPrice())
            product.setPrice(product1.getPrice());
        when(repository.save(product)).thenReturn(product);
        Product proResult = service.addProduct(product);

        assertEquals(1, proResult.getPid());
        assertEquals("soap1", proResult.getName());
        assertEquals("50", proResult.getPrice());
        assertEquals("sandal wood", proResult.getDescription());
    }




}
