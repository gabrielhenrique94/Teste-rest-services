package httpClient;

/**
 * Classe que representa resposta de uma requisição http
 */
public class RespostaHttp {
    private int status;
    private String conteudo;

    /**
     * Método que altera o valor do atributo conteudo da resposta
     * @param conteudo novo valor para o conteudo
     */
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    /**
     * Método que altera o valor do atributo status da resposta
     * @param status novo valor do atributo status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Método que retorna o status da resposta
     * @return status da requisição
     */
    public int getStatus() {
        return status;
    }

    /**
     * Método que retorna o conteudo da mensagem
     * @return String com o conteudo da resposta
     */
    public String getConteudo() {
        return conteudo;
    }
}
