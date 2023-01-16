package eif.viko.lt.project.simulith.audit.repository;

import java.util.Optional;

import eif.viko.lt.project.simulith.audit.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eif.viko.lt.project.simulith.audit.models.ERole;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
