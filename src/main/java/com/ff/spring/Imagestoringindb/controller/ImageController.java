package com.ff.spring.Imagestoringindb.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	public ResponseEntity<?> getImageById(@PathVariable int id){
		 Image image = service.downloadImage(id);
	        if (image == null) {
	            return ResponseEntity.notFound().build();
	        }
	        // Load image from file system or blob storage based on the path
	        File file = new File(image.getPath());
	        if (!file.exists()) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image not found on server");
	        }
	        try {
	            byte[] bytes = Files.readAllBytes(file.toPath());
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.IMAGE_JPEG); // Adjust content type based on your image type
	            headers.setContentLength(bytes.length);
	            return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
	        } catch (IOException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading image file");
	        }
	}
	
	
	
	
	
	/*
	@GetMapping("/download/{id}")
	public ResponseEntity<String> downloadImage(@PathVariable int id,HttpServletResponse response) throws IOException{
		InputStream  resource=service.downloadImage(path, id);
		
		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		
		StreamUtils.copy(resource, response.getOutputStream());
		
		
	} */
}
