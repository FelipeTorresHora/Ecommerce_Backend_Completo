package felipe.proj.ecombackend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class CarrinhoDto {
    private Long carrinhoId;
    private Set<CarrinhoItemDto> items;
    private BigDecimal PrecoTotal;
}
