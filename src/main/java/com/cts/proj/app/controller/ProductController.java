package com.cts.proj.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cts.proj.app.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	ProductService prodService;
	
	@GetMapping(path="/products/all")
	public List<String> allProducts(){
		return prodService.getAllProducts();
	}
	
	@GetMapping(path="/products/{code}")
	public String getProduct(@PathVariable("code") int prodCode) {
		return prodService.getAllProducts().get(prodCode);
	}
}
