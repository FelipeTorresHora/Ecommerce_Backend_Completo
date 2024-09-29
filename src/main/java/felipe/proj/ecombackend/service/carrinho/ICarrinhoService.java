package felipe.proj.ecombackend.service.carrinho;

import felipe.proj.ecombackend.dto.CarrinhoDto;
import felipe.proj.ecombackend.model.Carrinho;
import felipe.proj.ecombackend.model.User;

import java.math.BigDecimal;

public interface ICarrinhoService {
    Carrinho getCarrinho(Long id);
    void clearCarrinho(Long id);
    BigDecimal getPrecoTotal(Long id);

    Carrinho initializeNewCarrinho(User user);

    Carrinho getCarrinhoByUserId(Long userId);

    CarrinhoDto convertToDto(Carrinho carrinho);
}
