package com.mabom.produits.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "nom", types = { Produit.class })
public interface ProduitProjection {
	public String getNom();
	public String getId();
}
