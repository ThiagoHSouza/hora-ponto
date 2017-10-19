package br.com.developer.horaponto.core.domain.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.developer.horaponto.core.domain.enums.StatusPoint;
import br.com.developer.horaponto.security.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "time_points")
@NoArgsConstructor
public class TimePoint {
	
	@Id
	private String id;
	private LocalDateTime point;
	private StatusPoint status;
	
	@DBRef
	private User user;
	
	@LastModifiedDate private LocalDateTime updateAt;	
	@CreatedDate private LocalDateTime createdAt;
	
	public TimePoint(User user, StatusPoint status) {
		super();
		this.user = user;
		this.status = status;
		this.point = LocalDateTime.now();
	}
	
	

}
