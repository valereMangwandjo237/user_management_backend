package tech.chillo.sa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tech.chillo.sa.services.UserService;

@SpringBootApplication
public class UsersMicroserviceApplication {
	@Autowired
	UserService userService;
	

	//@PostConstruct
	void init_user() {
		//ajout les roles
//		userService.addRole(new Role(null, "ADMIN"));
//		userService.addRole(new Role(null, "USER"));
//		
//		ajout les users
//		userService.saveUser(new User(null, "valere", "sokcellerie", true, null));
//		userService.saveUser(new User(null, "fanny", "123", true, null));
//		userService.saveUser(new User(null, "admin", "123", true, null));
//		
//		//donner les roles aux users
//		userService.addRoleToUSer("admin", "ADMIN");
//		userService.addRoleToUSer("admin", "USER");
//		userService.addRoleToUSer("valere", "USER");
//		userService.addRoleToUSer("fanny", "USER");
		
	}

	
	public static void main(String[] args) {
		SpringApplication.run(UsersMicroserviceApplication.class, args);
	}

}
