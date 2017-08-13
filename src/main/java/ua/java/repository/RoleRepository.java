package ua.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.java.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
