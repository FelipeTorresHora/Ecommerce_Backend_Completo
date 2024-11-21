package felipe.proj.ecombackend.service.categoria;

import felipe.proj.ecombackend.model.Categoria;
import java.util.List;

public interface ICategoriaService {
    Categoria addCategoria(Categoria categoria);
    Categoria updateCategoria(Categoria categoria,Long id);
    void deleteCategoriaById(Long id);
    Categoria getCategoriaById(Long id);
    Categoria getCategoriaByNome(String nome);
    List<Categoria> getAllCategorias();
}
