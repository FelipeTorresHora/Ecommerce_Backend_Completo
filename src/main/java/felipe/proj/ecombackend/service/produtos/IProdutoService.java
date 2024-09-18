package felipe.proj.ecombackend.service.produtos;

import felipe.proj.ecombackend.model.Produto;
import felipe.proj.ecombackend.request.AddProdutoRequest;
import felipe.proj.ecombackend.request.UpdateProdutoRequest;

import java.util.List;

public interface IProdutoService {
    Produto addProduto(AddProdutoRequest produto);
    Produto getProdutoById(Long id);
    Produto updateProduto(UpdateProdutoRequest produto, Long produtoId);
    void deleteProduto(Long id);
    List<Produto> getAllProdutos();
    List<Produto> getProdutoByNome(String nome);
    List<Produto> getProdutoByCategoria(String categoria);
    List<Produto> getProdutoByMarca(String marca);
}
