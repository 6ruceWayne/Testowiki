package ua.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.java.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

	User findByEmail(String email);
}
