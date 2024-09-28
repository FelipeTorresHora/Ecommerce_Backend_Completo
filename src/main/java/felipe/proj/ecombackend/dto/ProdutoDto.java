package felipe.proj.ecombackend.dto;

import felipe.proj.ecombackend.model.Categoria;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProdutoDto {
    private Long id;
    private String nome;
    private String marca;
    private BigDecimal preco;
    private int inventario;
    private String descricao;
    private Categoria categoria;
}
