package com.ff.spring.Imagestoringindb.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ff.spring.Imagestoringindb.entity.Image;
import com.ff.spring.Imagestoringindb.service.ImageService;

@RestController
@RequestMapping("/system")
public class ImageController {
	
	@Autowired
	private ImageService service;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/upload")
	public ResponseEntity<Image> insertImage(@ModelAttribute Image image,@RequestParam("image") MultipartFile photofile){
		
		try {
			 return service.saveImage(image, path, photofile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/download/{id}")
	public ResponseEntity<Image> getImageById(@PathVariable int id){
		 Image image = service.downloadImage(id);
	        if (image == null) {
	            return ResponseEntity.notFound().build();
	        }
	            return new ResponseEntity<Image>(image,HttpStatus.OK);  
	}	
}
