package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = false)
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;


	@JsonProperty("id")
	private int id;

	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("brand")
	private String brand;
	
	@JsonProperty("category")
	private String category;
	
	@JsonProperty("thumbnail")
	private String thumbnail;
	
	@Override
    public String toString() {
        return new StringBuffer("id: ").append(this.id)
                .append(" description : ").append(this.description)
                .append(" category : ").append(this.category)
                .append(" thumbnail : ").append(this.thumbnail)
                .append(" brand : ").append(this.brand).toString();
    }
	
//	@JsonProperty("images")
//	private List<String> images;
}
