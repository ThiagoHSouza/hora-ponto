package br.com.developer.horaponto.security.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.developer.horaponto.security.JwtUser;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Document(collection = "users")
@AllArgsConstructor
public class User {

    @Id
    private String id;
    private String username;
    private String fullName;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    
    @JsonIgnore
    private Boolean enabled;
    
    @JsonIgnore
    private Date lastPasswordResetDate;
        
    @JsonIgnore
    private List<Authority> authorities;
    
    public User(){
    	
    }
    
    public User(JwtUser user){
    	this.id = user.getId();
    }

    @SuppressWarnings("serial")
	public User(String username, String password, String fullName){
    	this(username, password, fullName, new ArrayList<Authority>(){{ add(Authority.ROLE_USER); }});
    }
    
	public User(String username, String password, String fullName, List<Authority> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.authorities = authorities;
		
		this.enabled = true;
		this.lastPasswordResetDate = new Date();
	}
    
    
    
    
}