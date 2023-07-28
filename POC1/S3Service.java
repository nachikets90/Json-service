package com.example.demo.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class S3Service {

	@Autowired
	private AmazonS3 s3Client;
	
	@Value("${application.bucket.name}")
	private String bucketName;
	

	
	
	 public void uploadFileToS3(File file) {
	      //  File fileObj = convertMultiPartFileToFile(file);
	       // String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		 String fileName=file.getName();
		 s3Client.putObject(new PutObjectRequest(bucketName, fileName, file));
		
		// s3Client.putObject(bucketName,key , file);
	     //   s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
	     //   fileObj.delete();
	        log.info( "File uploaded to s3 server : "+fileName); 
	    }
	 
	  public byte[] downloadFile(String fileName) {
	        S3Object s3Object = s3Client.getObject(bucketName, fileName);
	        S3ObjectInputStream inputStream = s3Object.getObjectContent();
	        try {
	            byte[] content = IOUtils.toByteArray(inputStream);
	            return content;
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	  
	  public String deleteFile(String fileName) {
	        s3Client.deleteObject(bucketName, fileName);
	        return fileName + " removed ...";
	    }
	  
	  
}
