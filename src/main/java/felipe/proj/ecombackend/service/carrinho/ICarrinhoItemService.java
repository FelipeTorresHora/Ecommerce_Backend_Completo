package felipe.proj.ecombackend.service.carrinho;

import felipe.proj.ecombackend.model.CarrinhoItem;

public interface ICarrinhoItemService {
    void addItemCarrinho(Long carrinhoId, Long produtoId, int quantidade);
    void removeItemCarrinho(Long carrinhoId, Long produtoId);
    void updateItemQuantidade(Long carrinhoId, Long produtoId, int quantidade);

    CarrinhoItem getCarrinhoItem(Long carrinhoId, Long produtoId);
}
