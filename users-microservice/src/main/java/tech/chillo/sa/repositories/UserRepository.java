package tech.chillo.sa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.chillo.sa.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
	Optional<User> findByEmail(String email);
}
