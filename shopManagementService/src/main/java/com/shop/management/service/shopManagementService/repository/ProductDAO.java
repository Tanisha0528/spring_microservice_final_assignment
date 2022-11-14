package com.shop.management.service.shopManagementService.repository;


import com.shop.management.service.shopManagementService.model.Category;
import com.shop.management.service.shopManagementService.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Product> getAllProducts()
    {
        String getAllProductsQuery="SELECT * FROM Product";
        return jdbcTemplate.query(getAllProductsQuery, new ProductMapper());
     }
    public List<Product> getAllProductById(int pid)
    {
        String getAllProductByIdQuery="SELECT * FROM Product  WHERE pid= ?";
        return jdbcTemplate.query(getAllProductByIdQuery, new ProductMapper(),new Object[]{pid});
     }
     public List<Product> getAllProductByName(String name)
    {
        String getAllProductByNameQuery="SELECT * FROM Product  WHERE name= ?";
        return jdbcTemplate.query(getAllProductByNameQuery, new ProductMapper(),new Object[]{name});
     }
     public List<Product> getAllProductByPrice(String price)
    {
        String getAllProductByPriceQuery="SELECT * FROM Product  WHERE price= ?";
        return jdbcTemplate.query(getAllProductByPriceQuery, new ProductMapper(),new Object[]{price});
     }
     public List<Product> getAllProductByDescription(String description)
    {
        String getAllProductByDescriptionQuery="SELECT * FROM Product  WHERE description= ?";
        return jdbcTemplate.query(getAllProductByDescriptionQuery, new ProductMapper(),new Object[]{description});
     }
    private static final class ProductMapper implements RowMapper
    {
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product=new Product();
            product.setPid(rs.getInt("pid"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getString("price"));
            product.setDescription(rs.getString("description"));
           return product;
        }
    }
}

