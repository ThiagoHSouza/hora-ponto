package br.com.developer.horaponto.users.service;

import java.util.Objects;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.developer.horaponto.security.model.User;
import br.com.developer.horaponto.security.repository.UserRepository;
import br.com.developer.horaponto.users.dto.RegisterUserRequest;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	private static final Pattern REGEX_PASSWORD = Pattern.compile("[^\\s]{6,}");
	private static final Pattern REGEX_USERNAME = Pattern.compile("[^\\s]{4,}");
	
	public void create(RegisterUserRequest userRequest) throws Exception{
		Objects.requireNonNull(userRequest.getUsername(), "Favor informar o username.");
		Objects.requireNonNull(userRequest.getPassword(), "Favor informar a senha.");
		Objects.requireNonNull(userRequest.getFullname(), "Favor informar o nome completo.");
		
		validateMatcher(REGEX_PASSWORD, userRequest.getPassword(), new Exception("Password inválido."));
		validateMatcher(REGEX_USERNAME, userRequest.getUsername(), new Exception("Username inválido."));
		
		User user = new User(userRequest.getUsername(), bCryptEncoder(userRequest.getPassword()), userRequest.getFullname());
		userRepository.save(user);		
	}
	
	private void validateMatcher(Pattern pattern, String str, Exception e) throws Exception {
		if(!pattern.matcher(str).matches()){
			throw e;
		}
	}

	private String bCryptEncoder(String rawString){
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		return bCrypt.encode(rawString);
	}
}
