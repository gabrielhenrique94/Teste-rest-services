package br.com.desafio.httpClient;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe que chama os serviços de inserção , deleção e listagem de clientes
 */
public class DesafioClient extends ClienteHttp {
    private static final String BASE_URL = "http://localhost:8080";

    /**
     * Método que realiza a inserção de clientes
     * @param facebookId id do usuario que será inserido ou busca no facebook
     * @return Objeto com o conteudo da resposta e o código de status
     */
    public RespostaHttp insereUser(String facebookId){
        String url = BASE_URL+"/person/";
        List<Parametro> parametros = new LinkedList<Parametro>();
        if(facebookId != null) {
            Parametro ParametroFacebookId = new Parametro("facebookId",facebookId);
            parametros.add(ParametroFacebookId);
        }
        return post(url, parametros);
    }

    /**
     * Remove o usuario com id especifica da base.
     * @param facebookId id do usuario que será removido do sistema
     * @return Objeto com o conteudo da resposta e o código de status.
     */
    public RespostaHttp removeUser(String facebookId){
        String url = BASE_URL+"/person/"+facebookId+"/";
        //nenhum parametro
        return delete(url, new LinkedList<Parametro>());
    }

    /**
     * método que lista todos os usuarios da base
     * @return Objeto com uma lista no formato Json, com todos os usuarios da base, e o código de status da requisição
     */
    public RespostaHttp listaUsers(){
        String url = BASE_URL+"/person/";
        //nenhum parametro
        return get(url, new LinkedList<Parametro>());
    }

    /**
     * Método que lista usuarios do sistema até um limite
     * @param  limit Limite superior para o número de usuarios
     * @return Objeto com uma lista no formato Json, com os usuarios da base até um limite limit,
     *         e o código de status da requisição
     */
    public RespostaHttp listaUsers(String limit){
        String url = BASE_URL+"/person/";
        List<Parametro> parametros = new LinkedList<Parametro>();
        Parametro ParametroLimit = new Parametro("limit", limit);
        parametros.add(ParametroLimit);
        return get(url, parametros);
    }
}
