package felipe.proj.ecombackend.repositorio;

import felipe.proj.ecombackend.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepositorio extends JpaRepository<Carrinho, Long> {
    Carrinho findByUserId(Long userId);
}
