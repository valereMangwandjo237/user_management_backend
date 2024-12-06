package com.mabom.produits.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mabom.produits.entities.Image;
import com.mabom.produits.services.ImageService;


@RestController
@RequestMapping("api/image")
@CrossOrigin
public class ImageController {
	
	@Autowired
	ImageService imgService;
	
	@PostMapping("/upload")
	public Image uploadImage(@RequestParam("image") MultipartFile file) throws IOException{
		return imgService.uploadImage(file);
	}
	
	@GetMapping("/get/info/{id}")
	public Image getImageDetails(@PathVariable Long id) throws IOException{
		return imgService.getImageDetail(id);
	}
	
	@GetMapping("/load/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable Long id) throws IOException{
		return imgService.getImage(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteImage(@PathVariable Long id) {
		imgService.deleteImage(id);
	}
	
	@PutMapping("/update")
	public Image updateImage(@RequestParam("image") MultipartFile file) throws IOException{
		return imgService.uploadImage(file);
	}

}
