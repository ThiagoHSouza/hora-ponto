package br.com.developer.horaponto.core.domain.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.developer.horaponto.core.domain.enums.StatusPoint;
import br.com.developer.horaponto.core.domain.model.TimePoint;
import br.com.developer.horaponto.core.domain.repository.TimePointRepository;
import br.com.developer.horaponto.security.model.User;

@Service
public class TimePointService {
	
	@Autowired
	private TimePointRepository timePointRepository;
	
	public List<TimePoint> findOfDay(LocalDate date){
		LocalDateTime startDate = LocalDateTime.of(date, LocalTime.MIN);
		LocalDateTime endDate = LocalDateTime.of(date, LocalTime.MAX);
		
		return timePointRepository.findByPointBetween(startDate, endDate);
	}
	
	public TimePoint checkPoint(User user){
		StatusPoint state = getToggleStatus(timePointRepository.findTopByOrderByPointDesc());
		TimePoint check = new TimePoint(user, state);
		return timePointRepository.save(check);
	}
	
	private StatusPoint getToggleStatus(TimePoint lastPoint){
		if(lastPoint != null){
			return StatusPoint.getToggle(lastPoint.getStatus());
		}
		return StatusPoint.ENTRY;
	}

}
