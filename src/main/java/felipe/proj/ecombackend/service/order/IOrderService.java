package felipe.proj.ecombackend.service.order;

import felipe.proj.ecombackend.dto.OrderDto;
import felipe.proj.ecombackend.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);
    List<OrderDto> getUserOrders(Long userId);

    OrderDto convertToDto(Order order);
}
