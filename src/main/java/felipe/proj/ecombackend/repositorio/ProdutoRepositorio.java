package felipe.proj.ecombackend.repositorio;

import felipe.proj.ecombackend.model.Categoria;
import felipe.proj.ecombackend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {

    List<Produto> findByNome(String nome);
    List<Produto> findByCategoriaNome(String categoria);
    List<Produto> findByMarca(String marca);

}
