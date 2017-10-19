package br.com.developer.horaponto.users.api.v1;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.developer.horaponto.security.JwtTokenUtil;
import br.com.developer.horaponto.security.JwtUser;
import br.com.developer.horaponto.users.dto.RegisterUserRequest;
import br.com.developer.horaponto.users.service.UserService;

@RestController
@RequestMapping("public/user")
public class UserApi {
	
	@Autowired
	private UserService userService;
	
	@Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @GetMapping
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }
    
    @GetMapping("Teste")
    public String ok(){
    	return "OK";
    }
    
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody RegisterUserRequest user) throws Exception{
		userService.create(user);			
		return ResponseEntity.ok().build();
    }
    

}
