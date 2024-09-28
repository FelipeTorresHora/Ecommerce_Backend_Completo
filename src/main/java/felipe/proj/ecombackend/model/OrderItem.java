package felipe.proj.ecombackend.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantidade;
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public OrderItem(Order order, Produto produto, int quantidade, BigDecimal preco) {
        this.order = order;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;

    }
}
