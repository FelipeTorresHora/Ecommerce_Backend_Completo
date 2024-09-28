package felipe.proj.ecombackend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {
    private Long produtoId;
    private String produtoNome;
    private String produtoMarca;
    private int quantidade;
    private BigDecimal preco;
}