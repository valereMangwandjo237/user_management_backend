package com.mabom.produits;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.mabom.produits.entities.Categorie;
import com.mabom.produits.entities.Produit;

@SpringBootApplication
public class ProduitsApplication implements CommandLineRunner{
	
	private RepositoryRestConfiguration repositoryRestConfiguration;
	
	public ProduitsApplication(RepositoryRestConfiguration repositoryRestConfiguration) {
		super();
		this.repositoryRestConfiguration = repositoryRestConfiguration;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProduitsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Produit.class, Categorie.class);
		
	}

}
