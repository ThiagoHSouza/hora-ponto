package br.com.developer.horaponto.rest.api.v1;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.developer.horaponto.core.domain.model.TimePoint;
import br.com.developer.horaponto.core.domain.repository.TimePointRepository;
import br.com.developer.horaponto.core.domain.service.TimePointService;
import br.com.developer.horaponto.security.JwtUser;
import br.com.developer.horaponto.security.model.User;

@RestController
@RequestMapping("check-point")
public class TimePointResource {
	
	@Autowired
	TimePointRepository timePointRepository;
	
	@Autowired
	TimePointService timePointService;
		
	@PostMapping
	public TimePoint checkPoint(){
		User user = new User((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return timePointService.checkPoint(user);
	}
	
	@GetMapping("/of-day/{strDate}")
	public List<TimePoint> getByDate(@PathVariable("strDate") String strDate){
		return timePointService.findOfDay(LocalDateTime.parse(strDate).toLocalDate());
	}
	
	@DeleteMapping
	public void delete(){
		timePointRepository.deleteAll();
	}
	

	
}
