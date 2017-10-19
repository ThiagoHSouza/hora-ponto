package br.com.developer.horaponto.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.developer.horaponto.security.model.User;

public interface UserRepository extends MongoRepository<User, Long> {
    User findByUsername(String username);
}
