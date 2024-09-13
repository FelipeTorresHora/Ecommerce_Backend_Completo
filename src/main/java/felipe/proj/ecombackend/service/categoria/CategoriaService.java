package felipe.proj.ecombackend.service.categoria;

import felipe.proj.ecombackend.excecao.ProcuraNaoEncontrada;
import felipe.proj.ecombackend.model.Categoria;
import felipe.proj.ecombackend.repositorio.CategoriaRepositorio;

import java.util.List;

public class CategoriaService implements ICategoriaService {

    private CategoriaRepositorio categoriaRepositorio;

    @Override
    public Categoria addCategoria(Categoria categoria) {
        return null;
    }

    @Override
    public Categoria updateCategoria(Categoria categoria, Long id) {
        return null;
    }

    @Override
    public void deleteCategoriaById(Long id) {

    }

    @Override
    public Categoria getCategoriaById(Long id) {
        return categoriaRepositorio.findById(id)
                .orElseThrow(()-> new ProcuraNaoEncontrada("Categoria não encontrada!"));
    }

    @Override
    public List<Categoria> getCategoriaByNome(String nome) {
        return categoriaRepositorio.findByNome(nome);
    }

    @Override
    public List<Categoria> getAllCategorias() {
        return categoriaRepositorio.findAll();
    }

}
