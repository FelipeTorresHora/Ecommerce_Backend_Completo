package felipe.proj.ecombackend.excecao;

public class ProdutoNaoEncontrado extends RuntimeException {
    public ProdutoNaoEncontrado(String menssagem) {
        super(menssagem);
    }
}
