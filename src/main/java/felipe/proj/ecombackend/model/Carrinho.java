package felipe.proj.ecombackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CarrinhoItem> items = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void addItem(CarrinhoItem item) {
        this.items.add(item);
        item.setCarrinho(this);
        updateMontanteTotal();
    }

    public void removeItem(CarrinhoItem item) {
        this.items.remove(item);
        item.setCarrinho(null);
        updateMontanteTotal();
    }

    private void updateMontanteTotal() {
        this.valorTotal = items.stream().map(item -> {
            BigDecimal precoUnitario = item.getPrecoUnitario();
            if (precoUnitario == null) {
                return  BigDecimal.ZERO;
            }
            return precoUnitario.multiply(BigDecimal.valueOf(item.getQuantidade()));
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void clearCarrinho(){
        this.items.clear();
        updateMontanteTotal();
    }
}
