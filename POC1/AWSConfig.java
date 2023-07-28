package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AWSConfig {
	
	@Value("${cloud.aws.credentials.access-key}")
	private String awsAccssKey;
	
	@Value("${cloud.aws.credentials.secret-key}")
	private String awsSecretKey;
	

	@Bean
	public AmazonS3 amazonS3() {
		AWSCredentials credentials = new BasicAWSCredentials(awsAccssKey, awsSecretKey);
		AmazonS3 s3Client = null;
		
		try {
			s3Client =  AmazonS3ClientBuilder
	                .standard()
	                .withCredentials(new AWSStaticCredentialsProvider(credentials))
	                .withRegion(Regions.US_EAST_1)
	                .build();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return s3Client;
		
	}
}
