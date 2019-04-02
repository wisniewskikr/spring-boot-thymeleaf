package pl.kwi.springboot.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.kwi.springboot.persistence.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(final String username);

}
