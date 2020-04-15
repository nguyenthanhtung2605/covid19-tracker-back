package io.soen487p3.webservice.covid19tracker.repository;

import io.soen487p3.webservice.covid19tracker.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}
