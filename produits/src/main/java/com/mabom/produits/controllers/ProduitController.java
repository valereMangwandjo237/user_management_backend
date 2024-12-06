package com.mabom.produits.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.mabom.produits.dto.ProduitDTO;
import com.mabom.produits.entities.Categorie;
import com.mabom.produits.entities.Produit;
import com.mabom.produits.repositories.CategoryRepository;
import com.mabom.produits.services.ProduitService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProduitController {
	private ProduitService produitService;
	private CategoryRepository categoryRepository;

	public ProduitController(ProduitService produitService, CategoryRepository categoryRepository) {
		this.produitService = produitService;
		this.categoryRepository = categoryRepository;
	}
	
	@GetMapping(path = "/all")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Produit> getAllProduits(){
		return this.produitService.getAllProduits();
	}
	
	@GetMapping(value = "getbyid/{id}", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ProduitDTO getProduit(@PathVariable Long id){
		ProduitDTO produit = produitService.getProduit(id);
		if (produit == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit non trouvé");
		}
		return this.produitService.getProduit(id);
	}
	
	@PostMapping(path = "addprod", consumes = APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void create(@RequestBody Produit produit) {
		if (produit.getCategorie() != null && produit.getCategorie().getId() != null) {
	        // Recharger la catégorie
	        Categorie categorie = this.categoryRepository.findById(produit.getCategorie().getId()).orElseThrow();
	        produit.setCategorie(categorie);
	    }
		this.produitService.saveProduit(produit);
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(value = "updateprod/{id}")
	public ProduitDTO updateProduit(@PathVariable Long id, @RequestBody Produit produit) {
		Produit p = produitService.convertToEntity(produitService.getProduit(id));
		
		if (p == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit non trouvé");
		}
		p.setNom(produit.getNom());
		p.setPrice(produit.getPrice());
		p.setCreatedAt(produit.getCreatedAt());
		
		if (produit.getCategorie() != null) {
			p.setCategorie(produit.getCategorie());
		}
		return produitService.saveProduit(p);
	}
	
	@DeleteMapping(value = "deleteprod/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteProduit(@PathVariable Long id) {
		Produit produit = produitService.convertToEntity(produitService.getProduit(id));
		if (produit == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit non trouvé");
		}
		produitService.deleteProduit(produit);
	}
	
	@GetMapping(value = "/prodcats/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Produit> getProduitsByCatId(@PathVariable Long id) {
		List<Produit> produits =  produitService.findByCategorieId(id);
		return produits;
		
	}
	
	@GetMapping(value = "/prodsByName/{nom}")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Produit> findByNomProduitContains(@PathVariable String nom){
		return produitService.findByNomContains(nom);
	}
	
	

}
