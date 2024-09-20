package felipe.proj.ecombackend.repositorio;

import felipe.proj.ecombackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositorio extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    User findByEmail(String email);
}
