package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductList;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	
	
	private final String  URL="https://dummyjson.com/products";
	private  String filePath="/Users/neha/Documents/fileTest/";
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private S3Service s3Service;
	
	
	
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
		final String fileName=filePath+System.currentTimeMillis()+".txt";
		File file= new File(fileName);
		ObjectMapper mapper = new ObjectMapper();
		
		prodList.forEach(obj->{
		
		
			try {
				mapper.writeValue(file, prodList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		s3Service.uploadFileToS3(file);
	}
	
}
