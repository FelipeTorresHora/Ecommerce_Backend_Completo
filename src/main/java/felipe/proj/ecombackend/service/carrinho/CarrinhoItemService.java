package felipe.proj.ecombackend.service.carrinho;
import felipe.proj.ecombackend.excecao.ProcuraNaoEncontrada;
import felipe.proj.ecombackend.model.Carrinho;
import felipe.proj.ecombackend.model.CarrinhoItem;
import felipe.proj.ecombackend.model.Produto;
import felipe.proj.ecombackend.repositorio.CarrinhoItemRepositorio;
import felipe.proj.ecombackend.repositorio.CarrinhoRepositorio;
import felipe.proj.ecombackend.service.produtos.IProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CarrinhoItemService implements ICarrinhoItemService{
    private final CarrinhoItemRepositorio carrinhoItemRepositorio;
    private final CarrinhoRepositorio carrinhoRepositorio;
    private final IProdutoService produtoService;
    private final ICarrinhoService carrinhoService;

    @Override
    public void addItemCarrinho(Long carrinhoId, Long produtoId, int quantidade) {
        Carrinho carrinho = carrinhoService.getCarrinho(carrinhoId);
        Produto produto = produtoService.getProdutoById(produtoId);
        System.out.println("\n\n=====================================================================================");
        System.out.println("The produto Id:" + produtoId);
        System.out.println("The produto:" + produto);
        System.out.println("\n\n=====================================================================================");

        CarrinhoItem carrinhoItem = carrinho.getItems()
                .stream()
                .filter(item -> item.getProduto().getId().equals(produtoId))
                .findFirst().orElse(new CarrinhoItem());
        if (carrinhoItem.getId() == null) {
            carrinhoItem.setCarrinho(carrinho);
            carrinhoItem.setProduto(produto);
            carrinhoItem.setQuantidade(quantidade);
            carrinhoItem.setPrecoUnitario(produto.getPreco());
        }
        else {
            carrinhoItem.setQuantidade(carrinhoItem.getQuantidade() + quantidade);
        }
        carrinhoItem.getPrecoTotal();
        carrinho.addItem(carrinhoItem);
        carrinhoItemRepositorio.save(carrinhoItem);
        carrinhoRepositorio.save(carrinho);
    }

    @Override
    public void removeItemCarrinho(Long carrinhoId, Long produtoId) {
        Carrinho carrinho = carrinhoService.getCarrinho(carrinhoId);
        CarrinhoItem itemToRemove = getCarrinhoItem(carrinhoId, produtoId);
        carrinho.removeItem(itemToRemove);
        carrinhoRepositorio.save(carrinho);
    }

    @Override
    public void updateItemQuantidade(Long carrinhoId, Long produtoId, int quantidade) {
        Carrinho carrinho = carrinhoService.getCarrinho(carrinhoId);
        carrinho.getItems()
                .stream()
                .filter(item -> item.getProduto().getId().equals(produtoId))
                .findFirst()
                .ifPresent(item -> {
                    item.setQuantidade(quantidade);
                    item.setPrecoUnitario(item.getProduto().getPreco());
                    item.getPrecoTotal();
                });
        BigDecimal valorTotal = carrinho.getItems()
                .stream().map(CarrinhoItem ::getPrecoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        carrinho.setValorTotal(valorTotal);
        carrinhoRepositorio.save(carrinho);
    }

    @Override
    public CarrinhoItem getCarrinhoItem(Long carrinhoId, Long produtoId) {
        Carrinho carrinho = carrinhoService.getCarrinho(carrinhoId);
        return  carrinho.getItems()
                .stream()
                .filter(item -> item.getProduto().getId().equals(produtoId))
                .findFirst().orElseThrow(() -> new ProcuraNaoEncontrada("Item não encontrado"));
    }
}
