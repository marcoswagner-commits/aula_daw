package net.ufjnet.gestaoobra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.ufjnet.gestaoobra.models.User;

public interface UserDAO extends JpaRepository<User, Integer> {
	

	@Query("SELECT obj FROM User obj WHERE obj.username =:username")
	User findByUsername(@Param("username") String username);
	
}
