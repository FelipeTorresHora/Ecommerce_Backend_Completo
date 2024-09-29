package felipe.proj.ecombackend.service.order;

import felipe.proj.ecombackend.dto.OrderDto;
import felipe.proj.ecombackend.excecao.ProcuraNaoEncontrada;
import felipe.proj.ecombackend.model.Carrinho;
import felipe.proj.ecombackend.model.Order;
import felipe.proj.ecombackend.model.OrderItem;
import felipe.proj.ecombackend.model.Produto;
import felipe.proj.ecombackend.model.enums.OrderStatus;
import felipe.proj.ecombackend.repositorio.OrderRepositorio;
import felipe.proj.ecombackend.repositorio.ProdutoRepositorio;
import felipe.proj.ecombackend.service.carrinho.CarrinhoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepositorio orderRepositorio;
    private final ProdutoRepositorio produtoRepository;
    private final CarrinhoService carrinhoService;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public Order placeOrder(Long userId) {
        Carrinho carrinho   = carrinhoService.getCarrinhoByUserId(userId);
        Order order = createOrder(carrinho);
        List<OrderItem> orderItemList = createOrderItems(order, carrinho);
        order.setOrderItems(new HashSet<>(orderItemList));
        order.setValorTotal(calculateTotalAmount(orderItemList));
        Order savedOrder = orderRepositorio.save(order);
        carrinhoService.clearCarrinho(carrinho.getId());
        return savedOrder;
    }

    private Order createOrder(Carrinho carrinho) {
        Order order = new Order();
        order.setUser(carrinho.getUser());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        return  order;
    }

    private List<OrderItem> createOrderItems(Order order, Carrinho carrinho) {
        return  carrinho.getItems().stream().map(carrinhoItem -> {
            Produto produto = carrinhoItem.getProduto();
            produto.setInventario(produto.getInventario() - carrinhoItem.getQuantidade());
            produtoRepository.save(produto);
            return  new OrderItem(
                    order,
                    produto,
                    carrinhoItem.getQuantidade(),
                    carrinhoItem.getPrecoUnitario());
        }).toList();
    }

    private BigDecimal calculateTotalAmount(List<OrderItem> orderItemList) {
        return  orderItemList
                .stream()
                .map(item -> item.getPreco()
                        .multiply(new BigDecimal(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public OrderDto getOrder(Long orderId) {
        return orderRepositorio.findById(orderId)
                .map(this :: convertToDto)
                .orElseThrow(() -> new ProcuraNaoEncontrada("Sem orders encontrada"));
    }

    @Override
    public List<OrderDto> getUserOrders(Long userId) {
        List<Order> orders = orderRepositorio.findByUserId(userId);
        return  orders.stream().map(this :: convertToDto).toList();
    }

    @Override
    public OrderDto convertToDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }
}
