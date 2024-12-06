package com.mabom.produits.services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mabom.produits.entities.Image;
import com.mabom.produits.repositories.ImageRepository;

@Service
public class ImageServiceImp implements ImageService {
	@Autowired
	ImageRepository imgRepo;

	@Override
	public Image uploadImage(MultipartFile file) throws IOException {
		Image image = new Image(null, file.getOriginalFilename(), file.getContentType(), file.getBytes(), null);
		return imgRepo.save(image);
	}

	@Override
	public Image getImageDetail(Long id) throws IOException {
		final Optional<Image> img = imgRepo.findById(id);
		
		return Image.builder()
				.IdImage(img.get().getIdImage())
				.nom(img.get().getNom())
				.type(img.get().getType())
				.image(img.get().getImage()).build();
	}

	@Override
	public ResponseEntity<byte[]> getImage(Long id) throws IOException {
		final Optional<Image> img = imgRepo.findById(id);
		
		return ResponseEntity
				.ok()
				.contentType(MediaType.valueOf(img.get().getType()))
				.body(img.get().getImage());
	}

	@Override
	public void deleteImage(Long id) {
		imgRepo.deleteById(id);
		
	}

}
