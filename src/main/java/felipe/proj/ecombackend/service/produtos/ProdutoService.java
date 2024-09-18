package felipe.proj.ecombackend.service.produtos;


import felipe.proj.ecombackend.excecao.ProdutoNaoEncontrado;
import felipe.proj.ecombackend.model.Categoria;
import felipe.proj.ecombackend.model.Produto;
import felipe.proj.ecombackend.repositorio.CategoriaRepositorio;
import felipe.proj.ecombackend.repositorio.ProdutoRepositorio;
import felipe.proj.ecombackend.request.AddProdutoRequest;
import felipe.proj.ecombackend.request.UpdateProdutoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService implements IProdutoService {

    private final CategoriaRepositorio categoriaRepositorio;
    private final ProdutoRepositorio produtoRepositorio;

    @Override
    public Produto addProduto(AddProdutoRequest request) {
        Categoria categoria = Optional.ofNullable(categoriaRepositorio.findByNome(request.getCategoria().getNome()))
            .orElseGet(()->{
                Categoria newCategoria = new Categoria(request.getCategoria().getNome());
                return categoriaRepositorio.save(newCategoria);
        });
        request.setCategoria(categoria);
        return produtoRepositorio.save(criarProduto(request, categoria));
    }

    private Produto criarProduto(AddProdutoRequest request, Categoria categoria) {
        return new Produto(
                request.getNome(),
                request.getMarca(),
                request.getPreco(),
                request.getInventario(),
                request.getDescricao(),
                categoria
        );
    }
    @Override
    public Produto updateProduto(UpdateProdutoRequest request, Long produtoId) {
        return produtoRepositorio.findById(produtoId)
                .map(produtoExistente -> upprodutoExistente(produtoExistente, request))
                .map(produtoRepositorio :: save)
                .orElseThrow(()-> new ProdutoNaoEncontrado("Produto não encontrado!!"));
    }

    private Produto upprodutoExistente(Produto produtoExistente, UpdateProdutoRequest request) {
        produtoExistente.setNome(request.getNome());
        produtoExistente.setMarca(request.getMarca());
        produtoExistente.setPreco(request.getPreco());
        produtoExistente.setInventario(request.getInventario());
        produtoExistente.setDescricao(request.getDescricao());

        Categoria categoria = categoriaRepositorio.findByNome(request.getCategoria().getNome());
        produtoExistente.setCategoria(categoria);
        return produtoExistente;
    }

    @Override
    public void deleteProduto(Long id) {
        produtoRepositorio.findById(id)
                .ifPresentOrElse(produtoRepositorio::delete, () -> {
                            throw new ProdutoNaoEncontrado("Não foi possivel encontrar esse produto!");
                });
    }

    @Override
    public List<Produto> getAllProdutos() {
        return produtoRepositorio.findAll();
    }

    @Override
    public Produto getProdutoById(Long id) {
        return produtoRepositorio.findById(id)
                .orElseThrow(()-> new ProdutoNaoEncontrado("Não foi possivel encontrar esse produto!"));
    }

    @Override
    public List<Produto> getProdutoByNome(String nome) {
        return produtoRepositorio.findByNome(nome);
    }

    @Override
    public List<Produto> getProdutoByCategoria(String categoria) {
        return produtoRepositorio.findByCategoriaNome(categoria);
    }

    @Override
    public List<Produto> getProdutoByMarca(String marca) {
        return produtoRepositorio.findByMarca(marca);
    }
}
