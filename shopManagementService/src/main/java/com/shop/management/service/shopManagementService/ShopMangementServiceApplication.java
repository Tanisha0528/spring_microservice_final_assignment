package com.shop.management.service.shopManagementService;

import com.shop.management.service.shopManagementService.model.Category;
import com.shop.management.service.shopManagementService.model.Product;
import com.shop.management.service.shopManagementService.repository.CategoryRepository;
import com.shop.management.service.shopManagementService.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
// implements CommandLineRunner
public class ShopMangementServiceApplication {



	public static void main(String[] args) {
		SpringApplication.run(ShopMangementServiceApplication.class, args);
	}
/*

	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;



	public ShopMangementServiceApplication(CategoryRepository categoryRepository,ProductRepository productRepository) {
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		categoryRepository.save(new Category("soap","nivea,lux,cinthol,mamaearth"));
		categoryRepository.save(new Category("shampoo","treseme,loreal,mamaearth"));
		categoryRepository.save(new Category("lipstick","mac,sephora,lakme"));

		categoryRepository.findAll().forEach(System.out::println);

		productRepository.save(new Product("soap1","50","sandal wood",new Category(1,"soap","nivea,lux,cinthol,mamaearth")));
		productRepository.save(new Product("shampoo1","500","for dry hair",new Category(2,"shampoo","treseme,loreal,mamaearth")));


		productRepository.findAll().forEach(System.out::println);
	}
*/
}
