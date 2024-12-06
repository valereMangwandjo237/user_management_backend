package com.mabom.produits.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mabom.produits.entities.Categorie;
import com.mabom.produits.entities.Produit;


@RepositoryRestResource(path = "rest")
public interface ProduitRepository extends JpaRepository<Produit, Long>{
	List<Produit> findByNom(String nom);
	List<Produit> findByNomContains(String nom);
	
	@Query("select p from Produit p where p.nom like %:nom and p.price > :price")
	List<Produit> findByNomPrice(@Param("nom") String nom,@Param("price") Double price);
	
	@Query("select p from Produit p where p.categorie = :categorie")
	List<Produit> findByCategorie(@Param("categorie") Categorie categorie);
	
	List<Produit> findByCategorieId(Long id);
	List<Produit> findByOrderByNomAsc();
	
	@Query("select p from Produit p order by p.nom ASC, p.price DESC")
	List<Produit> trierProduitsNomsPrix();
}
