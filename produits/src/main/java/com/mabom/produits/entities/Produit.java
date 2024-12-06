package com.mabom.produits.entities;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private Double price;
	private Date createdAt;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn()
	private Categorie categorie;
	
	@OneToOne
	private Image image;
	

	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", price=" + price + ", createdAt=" + createdAt + ", categorie="
				+ categorie.getNom() + "]";
	}

	
	
	

}
