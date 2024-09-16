package felipe.proj.ecombackend.service.categoria;

import felipe.proj.ecombackend.excecao.ExistenteException;
import felipe.proj.ecombackend.excecao.ProcuraNaoEncontrada;
import felipe.proj.ecombackend.model.Categoria;
import felipe.proj.ecombackend.repositorio.CategoriaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService implements ICategoriaService {

    private final CategoriaRepositorio categoriaRepositorio;

    @Override
    public Categoria addCategoria(Categoria categoria) {
        return Optional.of(categoria).filter(c -> !categoriaRepositorio.existeByNome(c.getNome()))
                .map(categoriaRepositorio :: save)
                .orElseThrow(() -> new ExistenteException(categoria.getNome()+"Já existe essa categoria."));
    }

    @Override
    public Categoria updateCategoria(Categoria categoria, Long id) {
        return Optional.ofNullable(getCategoriaById(id)).map(categoriaAntiga -> {
            categoriaAntiga.setNome(categoria.getNome());
            return categoriaRepositorio.save(categoriaAntiga);
        }).orElseThrow(()-> new ProcuraNaoEncontrada("Categoria não encontrada!"));
    }

    @Override
    public void deleteCategoriaById(Long id) {
        categoriaRepositorio.findById(id)
                .ifPresentOrElse(categoriaRepositorio::delete, () -> {
                    throw new ProcuraNaoEncontrada("Não foi possivel encontrar essa categoria!");
                });
    }
    @Override
    public Categoria getCategoriaById(Long id) {
        return categoriaRepositorio.findById(id)
                .orElseThrow(()-> new ProcuraNaoEncontrada("Categoria não encontrada!"));
    }

    @Override
    public Categoria getCategoriaByNome(String nome) {
        return categoriaRepositorio.findByNome(nome);
    }

    @Override
    public List<Categoria> getAllCategorias() {
        return categoriaRepositorio.findAll();
    }

}
