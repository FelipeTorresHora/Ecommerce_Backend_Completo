package felipe.proj.ecombackend.controller;
import felipe.proj.ecombackend.excecao.ProcuraNaoEncontrada;
import felipe.proj.ecombackend.model.Carrinho;
import felipe.proj.ecombackend.model.User;
import felipe.proj.ecombackend.response.ApiResponse;
import felipe.proj.ecombackend.service.carrinho.ICarrinhoItemService;
import felipe.proj.ecombackend.service.carrinho.ICarrinhoService;
import felipe.proj.ecombackend.service.user.IUserService;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/carrinhoItems")
public class CarrinhoItemController {
    private final ICarrinhoItemService carrinhoItemService;
    private final ICarrinhoService carrinhoService;
    private final IUserService userService;


    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCarrinho(
            @RequestParam Long produtoId,
            @RequestParam Integer quantidade) {
        try {
            User user = userService.getAuthenticatedUser();
            Carrinho carrinho= carrinhoService.initializeNewCarrinho(user);
            carrinhoItemService.addItemCarrinho(carrinho.getId(), produtoId, quantidade);
            return ResponseEntity.ok(new ApiResponse("Item adcionado ao carrinho", null));
        } catch (ProcuraNaoEncontrada e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }catch (JwtException e){
            return  ResponseEntity.status(UNAUTHORIZED).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/carrinho/{carrinhoId}/item/{itemId}/remove")
    public ResponseEntity<ApiResponse> removeItemFromCarrinho(@PathVariable Long carrinhoId, @PathVariable Long itemId) {
        try {
            carrinhoItemService.removeItemCarrinho(carrinhoId, itemId);
            return ResponseEntity.ok(new ApiResponse("Item removido do carrinho", null));
        } catch (ProcuraNaoEncontrada e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/carrinho/{carrinhoId}/item/{itemId}/update")
    public  ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long carrinhoId,
                                                           @PathVariable Long produtoId,
                                                           @RequestParam Integer quantidade) {
        try {
            carrinhoItemService.updateItemQuantidade(carrinhoId, produtoId, quantidade);
            return ResponseEntity.ok(new ApiResponse("Item atualizado!", null));
        } catch (ProcuraNaoEncontrada e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }

    }
}
