package tech.chillo.sa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.chillo.sa.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRole(String role);
}
