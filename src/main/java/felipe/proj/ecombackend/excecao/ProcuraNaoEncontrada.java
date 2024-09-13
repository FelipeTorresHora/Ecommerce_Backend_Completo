package felipe.proj.ecombackend.excecao;

public class ProcuraNaoEncontrada extends RuntimeException{
    public ProcuraNaoEncontrada(String menssagem){
        super(menssagem);
    }
}
