package felipe.proj.ecombackend.service.produtos;


import felipe.proj.ecombackend.excecao.ProdutoNaoEncontrado;
import felipe.proj.ecombackend.model.Produto;
import felipe.proj.ecombackend.repositorio.ProdutoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService implements IPordutoService {

    private ProdutoRepositorio produtoRepositorio;

    @Override
    public Produto addProduto(Produto produto) {
        return null;
    }

    @Override
    public void updateProduto(Produto produto) {

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
        return produtoRepositorio.findByCategoria(categoria);
    }

    @Override
    public List<Produto> getProdutoByMarca(String marca) {
        return produtoRepositorio.findByMarca(marca);
    }
}
