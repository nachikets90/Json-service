package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ProductList;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/json")
public class JsonController {

	@Autowired
	private ProductService service;
	
	@GetMapping
	public ProductList callJsonService() {
		return service.consumeService();
	}
}
