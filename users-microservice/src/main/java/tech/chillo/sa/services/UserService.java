package tech.chillo.sa.services;

import java.util.List;

import tech.chillo.sa.entities.Role;
import tech.chillo.sa.entities.User;
import tech.chillo.sa.register.RegistrationRequest;

public interface UserService {
	User saveUser(User user);
	User findUserByUsername(String username);
	Role addRole(Role role);
	User addRoleToUSer(String username, String rolename);
	List<User> findAllUsers();
	User registerUser(RegistrationRequest request);
	public User validateToken(String code);
}
