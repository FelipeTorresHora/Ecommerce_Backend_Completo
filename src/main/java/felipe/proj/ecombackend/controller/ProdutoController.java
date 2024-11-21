package felipe.proj.ecombackend.controller;

import felipe.proj.ecombackend.excecao.ExistenteException;
import felipe.proj.ecombackend.request.AddProdutoRequest;
import felipe.proj.ecombackend.request.UpdateProdutoRequest;
import felipe.proj.ecombackend.response.ApiResponse;
import felipe.proj.ecombackend.service.produtos.IProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import felipe.proj.ecombackend.model.Produto;

import java.util.List;

import static org.springframework.http.HttpStatus.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/produto")
public class ProdutoController {
    private final IProdutoService produtoService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProdutos() {
        List<Produto> produtos = produtoService.getAllProdutos();
        return  ResponseEntity.ok(new ApiResponse("Sucesso!", produtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProdutoById(@PathVariable Long id){
        try {
            Produto produtos = produtoService.getProdutoById(id);
            return ResponseEntity.ok(new ApiResponse("De acordo com o id o produto é: ", produtos));
        } catch (ExistenteException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/{nome}")
    public ResponseEntity<ApiResponse> getProdutoByNome(@PathVariable String nome){
        try {
            List<Produto> produtos = produtoService.getProdutoByNome(nome);
            return ResponseEntity.ok(new ApiResponse("Produto informada: ", produtos));
        } catch (ExistenteException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/{categoria}")
    public ResponseEntity<ApiResponse> getProdutoByCategoria(@PathVariable String categoria){
        try {
            List<Produto> produtos = produtoService.getProdutoByCategoria(categoria);
            return ResponseEntity.ok(new ApiResponse("Produto informada: ", produtos));
        } catch (ExistenteException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/{marca}")
    public ResponseEntity<ApiResponse> getProdutoByMarca(@PathVariable String marca){
        try {
            List<Produto> produtos = produtoService.getProdutoByMarca(marca);
            return ResponseEntity.ok(new ApiResponse("Produto informada: ", produtos));
        } catch (ExistenteException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduto(@RequestBody AddProdutoRequest produto){
        try {
            Produto produtos = produtoService.addProduto(produto);
            return ResponseEntity.ok(new ApiResponse("Produto adicionado com sucesso: ", produtos));
        } catch (ExistenteException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PutMapping ("/{produtoId}/update")
    public ResponseEntity<ApiResponse> updateProduto(@RequestBody UpdateProdutoRequest request, @PathVariable Long produtoId){
        try {
            Produto produtos = produtoService.updateProduto(request, produtoId);
            return ResponseEntity.ok(new ApiResponse("Produto atualizado com sucesso: ", produtos));
        } catch (ExistenteException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @DeleteMapping ("/{produtoId}/delete")
    public ResponseEntity<ApiResponse> deleteProduto(@PathVariable Long produtoId){
        try {
            produtoService.deleteProduto(produtoId);
            return ResponseEntity.ok(new ApiResponse("Produto deletado com sucesso: ", produtoId));
        } catch (ExistenteException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

}
