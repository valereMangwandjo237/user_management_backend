package tech.chillo.sa.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.chillo.sa.entities.Role;
import tech.chillo.sa.entities.User;
import tech.chillo.sa.entities.VerificationToken;
import tech.chillo.sa.exceptions.EmailAlreadyExistsException;
import tech.chillo.sa.exceptions.ExpiredTokenException;
import tech.chillo.sa.exceptions.InvalidTokenException;
import tech.chillo.sa.register.RegistrationRequest;
import tech.chillo.sa.repositories.RoleRepository;
import tech.chillo.sa.repositories.UserRepository;
import tech.chillo.sa.repositories.VerificationTokenRepository;
import tech.chillo.sa.util.EmailSender;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	VerificationTokenRepository verifTokenRep;
	
	@Autowired
	EmailSender emailSender;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Role addRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public User addRoleToUSer(String username, String rolename) {
		User user = userRepository.findByUsername(username);
		Role role = roleRepository.findByRole(rolename);
		
		user.getRoles().add(role);
		return user;
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User registerUser(RegistrationRequest request) {
		Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
		if(optionalUser.isPresent()) {
			throw new EmailAlreadyExistsException("Email déja existant!");
		}
		
		User newUser = new User();
		newUser.setEmail(request.getEmail());
		newUser.setUsername(request.getUsername());
		newUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
		newUser.setEnabled(false);
		
		userRepository.save(newUser);
		
		//ajout du role par defaut "USER"
		Role r = roleRepository.findByRole("USER");
		List<Role> roles = new ArrayList<>();
		roles.add(r);
		newUser.setRoles(roles);
		
		userRepository.save(newUser);
		
		//generate code
		String code = this.generateCode();
		VerificationToken token = new VerificationToken(code, newUser);
		verifTokenRep.save(token);
		
		//envoie de code par mail
		sendEmailUser(newUser, token.getToken());
		
		return newUser;
	}
	
	public String generateCode() {
		Random random = new Random();
		Integer code = 100000 + random.nextInt(900000);
		return code.toString();
	}
	
	public void sendEmailUser(User u, String code) {
		String body = "<p>Salut " + u.getUsername()
				+ " votre code de verification est: </p><h1>" + code + "</h1>";
		emailSender.sendEmail(u.getEmail(), body);
	}

	@Override
	public User validateToken(String code) {
		VerificationToken token = verifTokenRep.findByToken(code);
		
		if(token == null)
			throw new InvalidTokenException("Code invalide!");
		
		User user = token.getUser();
		Calendar calendar = Calendar.getInstance();
		
		if((token.getTokenExpirationTime().getTime() - calendar.getTime().getTime()) <= 0 )
			throw new ExpiredTokenException("Le code a expiré!");
		
		user.setEnabled(true);
		return userRepository.save(user);
	}

}
