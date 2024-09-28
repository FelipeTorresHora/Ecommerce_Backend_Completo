package felipe.proj.ecombackend.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CarrinhoItemDto {
    private Long itemId;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
    private ProdutoDto product;
}
