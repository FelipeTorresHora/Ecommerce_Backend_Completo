package felipe.proj.ecombackend.service.carrinho;

import felipe.proj.ecombackend.dto.CarrinhoDto;
import felipe.proj.ecombackend.excecao.ProcuraNaoEncontrada;
import felipe.proj.ecombackend.model.Carrinho;
import felipe.proj.ecombackend.repositorio.CarrinhoItemRepositorio;
import felipe.proj.ecombackend.repositorio.CarrinhoRepositorio;
import felipe.proj.ecombackend.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import felipe.proj.ecombackend.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarrinhoService implements ICarrinhoService{
    private final CarrinhoRepositorio carrinhoRepositorio;
    private final CarrinhoItemRepositorio carrinhoItemRepositorio;
    private final ModelMapper modelMapper;
    private final IUserService userService;

    @Override
    public Carrinho getCarrinho(Long carrinhoId) {
        Carrinho carrinho = carrinhoRepositorio.findById(carrinhoId)
                .orElseThrow(() -> new ProcuraNaoEncontrada("Carrinho não encontrado"));
        BigDecimal valorTotal = carrinho.getValorTotal();
        carrinho.setValorTotal(valorTotal);
        return carrinhoRepositorio.save(carrinho);
    }


//        @Override
//    public Carrinho getCarrinho(Long userId) {
//        return Optional.ofNullable(getCarrinhoByUserId(userId))
//                .map(carrinho -> {
//                    // If the carrinho exists, return it
//                    BigDecimal valorTotal = carrinho.getValorTotal();
//                    carrinho.setValorTotal(valorTotal);
//                    return carrinhoRepositorio.save(carrinho);
//                })
//                .orElseGet(() -> {
//                    // If the carrinho does not exist, initialize a new carrinho
//                    User user = Optional.ofNullable(userService.getUserById(userId))
//                            .orElseThrow(() -> new ProcuraNaoEncontrada("Usuario não encontrado"));
//                    return initializeNewCarrinho(user);
//                });
//    }

    @Transactional
    @Override
    public void clearCarrinho(Long id) {
        Carrinho carrinho = getCarrinho(id);
        carrinhoItemRepositorio.deleteAllByCarrinhoId(id);
        carrinho.clearCarrinho();
        carrinhoRepositorio.deleteById(id);
    }

    @Override
    public BigDecimal getPrecoTotal(Long id) {
        Carrinho carrinho = getCarrinho(id);
        return carrinho.getValorTotal();
    }


    @Override
    public Carrinho initializeNewCarrinho(User user) {
        return Optional.ofNullable(getCarrinhoByUserId(user.getId()))
                .orElseGet(() -> {
                    Carrinho carrinho = new Carrinho();
                    carrinho.setUser(user);
                    return carrinhoRepositorio.save(carrinho);
                });
    }

    @Override
    public Carrinho getCarrinhoByUserId(Long userId) {
        return carrinhoRepositorio.findByUserId(userId);
    }

    @Override
    public CarrinhoDto convertToDto(Carrinho carrinho){
        return modelMapper.map(carrinho, CarrinhoDto.class);
    }
}
