package com.mabom.produits.services;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.mabom.produits.entities.Image;

public interface ImageService {
	Image uploadImage(MultipartFile file) throws IOException;
	Image getImageDetail(Long id) throws IOException;
	ResponseEntity<byte[]> getImage(Long id) throws IOException;
	void deleteImage(Long id);
}
