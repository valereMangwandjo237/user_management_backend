package com.mabom.produits.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mabom.produits.dto.ProduitDTO;
import com.mabom.produits.entities.Categorie;
import com.mabom.produits.entities.Produit;
import com.mabom.produits.repositories.ProduitRepository;

@Service
public class ProduitServiceImp implements ProduitService{
	private final ProduitRepository produitRepository;

	public ProduitServiceImp(ProduitRepository produitRepository) {
		this.produitRepository = produitRepository;
	}

	@Override
	public ProduitDTO saveProduit(Produit p) {
		return convertEntityToDto(produitRepository.save(p));
	}

	@Override
	public Produit updateProduit(Produit p) {
		return produitRepository.save(p);
	}

	@Override
	public void deleteProduit(Produit produit) {
		produitRepository.delete(produit);
		
	}

	@Override
	public void deleteProduitById(Long id) {
		produitRepository.deleteById(id);
		
	}

	@Override
	public ProduitDTO getProduit(Long id) {
		return convertEntityToDto(produitRepository.findById(id).orElse(null));
	}

	@Override
	public List<Produit> getAllProduits() {
		List<Produit> prods = produitRepository.findAll();
		
		return prods;
	}

	@Override
	public List<Produit> findByNom(String nom) {
		return produitRepository.findByNom(nom);
	}

	@Override
	public List<Produit> findByNomContains(String nom) {
		return produitRepository.findByNomContains(nom);
	}

	@Override
	public List<Produit> findByNomPrice(String nom, Double price) {
		return produitRepository.findByNomPrice(nom, price);
	}

	@Override
	public List<Produit> findByCategorie(Categorie categorie) {
		return produitRepository.findByCategorie(categorie);
	}

	@Override
	public List<Produit> findByCategorieId(Long id) {
		return produitRepository.findByCategorieId(id);
	}

	@Override
	public List<Produit> findByOrderByNomAsc() {
		return produitRepository.findByOrderByNomAsc();
	}

	@Override
	public List<Produit> trierProduitsNomsPrix() {
		return produitRepository.trierProduitsNomsPrix();
	}

	@Override
	public ProduitDTO convertEntityToDto(Produit produit) {
		ProduitDTO produitDTO = new ProduitDTO();
		produitDTO.setId(produit.getId());
		produitDTO.setNom(produit.getNom());
		produitDTO.setPrice(produit.getPrice());
		produitDTO.setCreatedAt(produit.getCreatedAt());
		produitDTO.setCategorie(produit.getCategorie());
		
		return produitDTO;
	}

	@Override
	public Produit convertToEntity(ProduitDTO produitDTO) {
		if (produitDTO == null) {
            return null;
        }

        Produit produit = new Produit();
        produit.setId(produitDTO.getId());
        produit.setNom(produitDTO.getNom());
        produit.setPrice(produitDTO.getPrice());
        produit.setCreatedAt(produitDTO.getCreatedAt());
        produit.setCategorie(produitDTO.getCategorie());
        
        return produit;
	}

}
