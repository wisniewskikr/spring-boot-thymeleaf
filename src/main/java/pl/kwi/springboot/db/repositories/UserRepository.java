package pl.kwi.springboot.db.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.kwi.springboot.db.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
}
