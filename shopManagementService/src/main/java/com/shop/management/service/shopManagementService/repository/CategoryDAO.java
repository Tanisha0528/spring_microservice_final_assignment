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
public class CategoryDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Category> getAllCategories()
    {
        String getAllCategoriesQuery="SELECT * FROM Category";
        return jdbcTemplate.query(getAllCategoriesQuery, new CategoryMapper());
    }
    public List<Category> getAllCategoryById(int id)
    {
        String getAllCategoryByIdQuery="SELECT * FROM Category  WHERE id= ?";
        return jdbcTemplate.query(getAllCategoryByIdQuery, new CategoryMapper(),new Object[]{id});
    }
    public List<Category> getAllCategoryByName(String name)
    {
        String getAllCategoryByNameQuery="SELECT * FROM Category  WHERE name= ?";
        return jdbcTemplate.query(getAllCategoryByNameQuery, new CategoryMapper(),new Object[]{name});
    }
    public List<Category> getAllCategoryByDescription(String description)
    {
        String getAllCategoryByDescriptionQuery="SELECT * FROM Category  WHERE description= ?";
        return jdbcTemplate.query(getAllCategoryByDescriptionQuery, new CategoryMapper(),new Object[]{description});
    }
    private static final class CategoryMapper implements RowMapper
    {
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Category category=new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            category.setDescription(rs.getString("description"));

            return category;
        }
    }
}
