package com.cts.proj.app.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
	private static List<String> products;
	
	public ProductService() {
		products = Arrays.asList("Helmet","Cerebro","Wheel Chair","Iron");
	}
	
	public List<String> getAllProducts(){
		log.info("Returning All products from "+this.getClass().getName());
		return products;
	}
}
