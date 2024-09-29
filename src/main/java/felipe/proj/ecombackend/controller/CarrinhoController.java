package felipe.proj.ecombackend.controller;
import felipe.proj.ecombackend.dto.CarrinhoDto;
import felipe.proj.ecombackend.excecao.ProcuraNaoEncontrada;
import felipe.proj.ecombackend.model.Carrinho;
import felipe.proj.ecombackend.response.ApiResponse;
import felipe.proj.ecombackend.service.carrinho.ICarrinhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/carrinhos")
public class CarrinhoController {
    private final ICarrinhoService carrinhoService;

    @GetMapping("/user/{userId}/my-carrinho")
    public ResponseEntity<ApiResponse> getUserCarrinho(@PathVariable Long userId) {
        try {
            Carrinho carrinho = carrinhoService.getCarrinhoByUserId(userId);
            CarrinhoDto carrinhoDto = carrinhoService.convertToDto(carrinho);
            return ResponseEntity.ok(new ApiResponse("Sucesso!", carrinhoDto));
        } catch (ProcuraNaoEncontrada e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{carrinhoId}/clear")
    public ResponseEntity<ApiResponse> clearCarrinho( @PathVariable Long carrinhoId) {
        try {
            carrinhoService.clearCarrinho(carrinhoId);
            return ResponseEntity.ok(new ApiResponse("Carrinho limpo com sucesso!", null));
        } catch (ProcuraNaoEncontrada e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{carrinhoId}/carrinho/total-price")
    public ResponseEntity<ApiResponse> getPrecoTotal( @PathVariable Long carrinhoId) {
        try {
            BigDecimal totalPrice = carrinhoService.getPrecoTotal(carrinhoId);
            return ResponseEntity.ok(new ApiResponse("Preco Total: ", totalPrice));
        } catch (ProcuraNaoEncontrada e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
