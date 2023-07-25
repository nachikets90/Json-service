package com.example.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URI;

import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductList;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	private RestTemplate restTemplate;
	
	private final String  URL="https://dummyjson.com/products";
	private final String filePath="/Users/neha/Documents/fileTest/abc.txt";
	
	public ProductList consumeService() {
		
		ProductList list=getListofProduct();
		return list;
	}
	
	
	
	private ProductList getListofProduct()
	 {
		ProductList prod=restTemplate.getForEntity(URL, ProductList.class).getBody();
		log.info("List fecheched "+prod);
		processData(prod);
		return prod;
	  }
	
	private void processData(ProductList list) {
		
		List<Product> prodList= list.getProducts();
		
		prodList.forEach(obj->{
		
			File file= new File(filePath);
			ObjectMapper mapper = new ObjectMapper();
			try {
				mapper.writeValue(file, prodList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
}
