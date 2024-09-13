package felipe.proj.ecombackend.service.produtos;

import felipe.proj.ecombackend.model.Produto;

import java.util.List;

public interface IPordutoService {
    Produto addProduto(Produto produto);
    Produto getProdutoById(Long id);
    void updateProduto(Produto produto);
    void deleteProduto(Long id);
    List<Produto> getAllProdutos();
    List<Produto> getProdutoByNome(String nome);
    List<Produto> getProdutoByCategoria(String categoria);
    List<Produto> getProdutoByMarca(String marca);
}
