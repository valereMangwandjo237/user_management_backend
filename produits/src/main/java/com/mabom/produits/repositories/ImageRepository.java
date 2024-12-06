package com.mabom.produits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mabom.produits.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{

}
