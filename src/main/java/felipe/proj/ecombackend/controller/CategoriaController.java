package felipe.proj.ecombackend.controller;

import felipe.proj.ecombackend.excecao.ExistenteException;
import felipe.proj.ecombackend.model.Categoria;
import felipe.proj.ecombackend.response.ApiResponse;
import felipe.proj.ecombackend.service.categoria.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/categoria")
public class CategoriaController {
    private final ICategoriaService categoriaService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategorias(){
        try{
            List<Categoria> categorias = categoriaService.getAllCategorias();
            return ResponseEntity.ok(new ApiResponse("Encontrei: ", categorias));
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error:", INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCategoriaById(@PathVariable Long id){
        try {
            Categoria nCategoria = categoriaService.getCategoriaById(id);
            return ResponseEntity.ok(new ApiResponse("De acordo com o id a categoria é: ", nCategoria));
        } catch (ExistenteException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/{nome}")
    public ResponseEntity<ApiResponse> getCategoriaByNome(@PathVariable String nome){
        try {
            Categoria nCategoria = categoriaService.getCategoriaByNome(nome);
            return ResponseEntity.ok(new ApiResponse("Categoria informada: ", nCategoria));
        } catch (ExistenteException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategoria(@RequestBody Categoria nome){
        try {
            Categoria nCategoria = categoriaService.addCategoria(nome);
            return ResponseEntity.ok(new ApiResponse("Adicionado com sucesso a categoria: ", nCategoria));
        } catch (ExistenteException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse> deleteCategoriaById(@PathVariable Long id){
        try {
            categoriaService.deleteCategoriaById(id);
            return ResponseEntity.ok(new ApiResponse("Categoria informada: ", null));
        } catch (ExistenteException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PutMapping("/{id}/atualizar")
    public ResponseEntity<ApiResponse> updateCategoria(@PathVariable Long id, @RequestBody Categoria categoria){
        try {
            Categoria uCategoria = categoriaService.updateCategoria(categoria, id);
            return ResponseEntity.ok(new ApiResponse("Categoria informada: ", uCategoria));
        } catch (ExistenteException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
}
