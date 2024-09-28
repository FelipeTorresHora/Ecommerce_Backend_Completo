package felipe.proj.ecombackend.repositorio;

import felipe.proj.ecombackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepositorio extends JpaRepository<Role, Long> {
    Optional<Role> findByNome(String role);
}