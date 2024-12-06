package com.mabom.produits.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mabom.produits.entities.Categorie;
import com.mabom.produits.repositories.CategoryRepository;


@RestController
@CrossOrigin
@RequestMapping("/api/cat")
public class CategoryController {
	private CategoryRepository categoryRepository;

	public CategoryController(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}
	
	@GetMapping()
	@ResponseStatus(value = HttpStatus.OK)
	public List<Categorie> getAllCategories() {
		return categoryRepository.findAll();
	}
	
	@GetMapping(value = "{id}", produces = APPLICATION_JSON_VALUE)
	public Categorie getCategpryById(@PathVariable Long id) {
		return categoryRepository.findById(id).get();
	}
}
