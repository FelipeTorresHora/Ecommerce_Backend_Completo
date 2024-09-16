package felipe.proj.ecombackend.request;

import felipe.proj.ecombackend.model.Categoria;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProdutoRequest {
    private Long id;
    private String nome;
    private String marca;
    private BigDecimal preco;
    private int inventario;
    private String descricao;
    Categoria categoria;
}
