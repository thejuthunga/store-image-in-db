package com.ff.spring.Imagestoringindb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ff.spring.Imagestoringindb.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer>{
	
}
