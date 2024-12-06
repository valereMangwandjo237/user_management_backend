package com.mabom.produits;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mabom.produits.entities.Categorie;
import com.mabom.produits.entities.Produit;
import com.mabom.produits.repositories.ProduitRepository;

@SpringBootTest
class ProduitsApplicationTests {

	@Autowired
	private ProduitRepository produitRepository;
	
	@Test
	public void testCreateProduit() {
	
	}
	
	@Test
	public void testFindProduit()
	{
	Produit p = produitRepository.findById(2L).get();
	System.out.println(p);
	}
	
	@Test
	public void testUpdateProduit()
	{
	Produit p = produitRepository.findById(2L).get();
	p.setPrice(1500.500);
	produitRepository.save(p);
	System.out.println(p);
	}
	
	@Test
	public void testDeleteProduit()
	{
		produitRepository.deleteById(3L);
	}
	
	@Test
	public void testFindAllProduit()
	{
		List<Produit> prods = produitRepository.findAll();
		for (Produit produit : prods) {
			System.out.println(produit);
		}
	}
	
	@Test
	public void testFindNom()
	{
		List<Produit> prods = produitRepository.findByNom("iphone");
		for (Produit produit : prods) {
			System.out.println(produit);
		}
	}
	
	@Test
	public void testFindByNomPrice()
	{
		List<Produit> prods = produitRepository.findByNomPrice("iphone", 1400.0);
		
		if (prods.isEmpty()) {
	        System.out.println("Aucun produit trouvé.");
	    } else {
	        for (Produit produit : prods) {
	            System.out.println(produit);
	        }
	    }
	}
	
	@Test
	public void testfindByCategorie()
	{
		Categorie cat = new Categorie();
		cat.setId(2L);
		List<Produit> prods = produitRepository.findByCategorie(cat);
		for (Produit produit : prods) {
			System.out.println(produit);
		}
	}
	
	@Test
	public void testFindByCategorieId()
	{
		List<Produit> prods = produitRepository.findByCategorieId(1L);
		
		if (prods.isEmpty()) {
	        System.out.println("Aucun produit trouvé.");
	    } else {
	        for (Produit produit : prods) {
	            System.out.println(produit);
	        }
	    }
	}
	
	@Test
	public void testFindByOrderByNomAsc()
	{
		List<Produit> prods = produitRepository.findByOrderByNomAsc();
		
        for (Produit produit : prods) {
            System.out.println(produit);
        }
	    
	}
	
	@Test
	public void testTrierProduitsNomsPrix()
	{
		List<Produit> prods = produitRepository.trierProduitsNomsPrix();
		
		for (Produit produit : prods) {
            System.out.println(produit);
        }
	}
	

}
