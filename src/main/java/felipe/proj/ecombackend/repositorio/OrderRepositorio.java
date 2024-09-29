package felipe.proj.ecombackend.repositorio;

import felipe.proj.ecombackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepositorio extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
