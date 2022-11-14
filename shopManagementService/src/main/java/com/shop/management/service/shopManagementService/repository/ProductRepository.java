package com.shop.management.service.shopManagementService.repository;

import com.shop.management.service.shopManagementService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ProductRepository")
public interface ProductRepository extends JpaRepository<Product,Integer> {

}
