package com.ff.spring.Imagestoringindb.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.ff.spring.Imagestoringindb.entity.Image;

public interface ImageService {
	
	public ResponseEntity<Image> saveImage(Image image,String path,MultipartFile file)throws IOException;
	
	public Image downloadImage(int id);
}
