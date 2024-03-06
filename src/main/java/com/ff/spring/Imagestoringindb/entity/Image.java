package com.ff.spring.Imagestoringindb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int imageid;
	
	@Column(nullable = false)
	private String path;
	
	@Column(unique = true)
	private String name;
	
	@Column(nullable = false)
	private String password;
	
	
}
