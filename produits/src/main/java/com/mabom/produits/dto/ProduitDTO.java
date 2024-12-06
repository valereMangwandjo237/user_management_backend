package com.mabom.produits.dto;

import java.util.Date;

import com.mabom.produits.entities.Categorie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProduitDTO {
	private Long id;
	private String nom;
	private Double price;
	private Date createdAt;
	private Categorie categorie;
}
