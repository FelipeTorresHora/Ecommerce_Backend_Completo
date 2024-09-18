package felipe.proj.ecombackend.repositorio;

import felipe.proj.ecombackend.model.Categoria;
import felipe.proj.ecombackend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
    Categoria findByNome(String nome);

    boolean getCategoriaByNome(String name);
}
