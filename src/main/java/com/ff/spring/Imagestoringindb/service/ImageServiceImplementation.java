package com.ff.spring.Imagestoringindb.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ff.spring.Imagestoringindb.entity.Image;
import com.ff.spring.Imagestoringindb.repository.ImageRepository;

@Service
public class ImageServiceImplementation implements ImageService{
	
	@Autowired
	private ImageRepository repository;

	
	//path --> is the path of images folder present in the project
	@Override
	public ResponseEntity<Image> saveImage(Image image, String path, MultipartFile file) throws IOException {
		
		
		//taking original file name from the client's file
		String name=file.getOriginalFilename();
		
		//creating path for the client file(image file) in project
		String filePath=path+File.separator+name;
		
		//creating folder if not created
		File f=new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		//coping file from MultipartFile object to filePath which is created in the method for that image.
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		image.setPath(filePath);
		
//		repository.save(image);
		
		return new ResponseEntity<Image>(repository.save(image),HttpStatus.CREATED);
	}


	@Override
	public Image downloadImage(int id) {
		Optional<Image> optional = repository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}


	

//	@Override
//	public InputStream downloadImage(String path, int id) throws FileNotFoundException {
//		//
//		String fullPath=path+File.separator+imageName;
//		//
//		InputStream inputStream=new FileInputStream(fullPath);
//		//
//		return inputStream;
//	}
//	
	

}
