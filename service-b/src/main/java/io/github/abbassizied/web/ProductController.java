package io.github.abbassizied.web;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

import io.github.abbassizied.models.Product;

@RestController
@RequestMapping("/products")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@GetMapping
	public List<Product> getProducts() {
		logger.info("Fetching products");
		return List.of(new Product(100L, "iPhone 15", 999.99), new Product(200L, "MacBook Pro", 2499.99));
	}
}
