package br.com.desafio.services;

import br.com.desafio.httpClient.GraphClient;
import br.com.desafio.models.FacebookUser;
import br.com.desafio.persistence.FacebookUserDAO;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.List;

/**
 * Classe que representa os serviços de inclusão e listagem de usuarios
 */
public class FacebookUserServices extends ServiceBase {
    @Override
    /**
     * Retorna todos os usuarios que estão no banco, até o limite passado como parametro da request</br>
     * Status 200: requisição volta com os usuarios</br>
     * Status 400: o parametro limit não é um número
     * @param request Objeto que representa a requisição enviada para o servidor
     * @param response Objeto que representa a resposta que será enviada para o cliente
     * @return String contendo a lista de usuarios , no formato JSON.
     */
    public String get(Request request, Response response) {
        //pegando o parametro limite
        int limite = 0;
        if(request.params("limit") != null){
            try{
                limite = Integer.parseInt(request.params("limit"));
            }catch (NumberFormatException ex){
                response.status(400);
                return "Incorrect limit format";
            }
        }
        response.status(200);//desnecessario, mas só pra deixar claro
        FacebookUserDAO dao = new FacebookUserDAO();
        List<FacebookUser> list = dao.lista(limite);
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    /**
     * Chama a graph API e salva o usuario retornado no banco;
     * @param request Objeto que representa a requisição enviada para o servidor
     * @param response Objeto que representa a resposta que será enviada para o cliente
     * @return String vazia, ou String contendo explicação do erro
     */

    @Override
    public String post(Request request, Response response) {
        //confere se o facebookId foi passado como parametro
        if(request.params("facebookId") == null){
            response.status(400);
            return "This request require a facebookId";
        }
        long facebookId = 0;
        try{
            facebookId = Long.parseLong(request.params("facebookId"));
        }catch (NumberFormatException ex){
            response.status(400);
            return "Incorrect facebookId format";
        }

        GraphClient client = new GraphClient();
        FacebookUser user = client.getFacebookUserById(facebookId);
        //confere se o facebook devolveu o usuario
        if(user != null) {
            FacebookUserDAO dao = new FacebookUserDAO();
            dao.salvar(user);
            response.status(201);
            return "";
        }else{
            response.status(400);
            return "Invalid facebook Id";
        }
    }

}
