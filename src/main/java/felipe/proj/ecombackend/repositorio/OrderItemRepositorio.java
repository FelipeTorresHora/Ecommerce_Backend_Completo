package felipe.proj.ecombackend.repositorio;

import felipe.proj.ecombackend.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepositorio extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByProdutoId(Long id);
}
