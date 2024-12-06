package com.mabom.produits.services;

import java.util.List;

import com.mabom.produits.dto.ProduitDTO;
import com.mabom.produits.entities.Categorie;
import com.mabom.produits.entities.Produit;

public interface ProduitService{
	ProduitDTO saveProduit(Produit p);
	Produit updateProduit(Produit p);
	void deleteProduit(Produit produit);
	void deleteProduitById(Long id);
	ProduitDTO getProduit(Long id);
	List<Produit> getAllProduits();
	List<Produit> findByNom(String nom);
	List<Produit> findByNomContains(String nom);
	List<Produit> findByNomPrice(String nom,Double price);
	List<Produit> findByCategorie(Categorie categorie);
	List<Produit> findByCategorieId(Long id);
	List<Produit> findByOrderByNomAsc();
	List<Produit> trierProduitsNomsPrix();
	
	ProduitDTO convertEntityToDto(Produit produit);
	Produit convertToEntity(ProduitDTO produitDTO);

}
