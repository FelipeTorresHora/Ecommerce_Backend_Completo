package felipe.proj.ecombackend.repositorio;

import felipe.proj.ecombackend.model.CarrinhoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarrinhoItemRepositorio extends JpaRepository<CarrinhoItem, Long> {
    void deleteAllByCarrinhoId(Long id);
    List<CarrinhoItem> findByProdutoId(Long productId);
}
