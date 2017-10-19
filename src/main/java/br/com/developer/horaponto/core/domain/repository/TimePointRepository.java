package br.com.developer.horaponto.core.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.developer.horaponto.core.domain.model.TimePoint;

public interface TimePointRepository extends MongoRepository<TimePoint, String>{

	List<TimePoint> findByPointBetween(LocalDateTime startDate, LocalDateTime endDate);
	TimePoint findTopByOrderByPointDesc();
}
