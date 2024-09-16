package felipe.proj.ecombackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String marca;
    private BigDecimal preco;
    private int inventario;
    private String descricao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Produto(String nome, String marca, BigDecimal preco, int inventario, String descricao, Categoria categoria) {
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.inventario = inventario;
        this.descricao = descricao;
        this.categoria = categoria;
    }
}
