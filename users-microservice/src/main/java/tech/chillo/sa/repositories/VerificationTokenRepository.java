package tech.chillo.sa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.chillo.sa.entities.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{
	VerificationToken findByToken(String token);
}
