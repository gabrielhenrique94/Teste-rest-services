package br.com.desafio.services;

import br.com.desafio.httpClient.GraphClient;
import br.com.desafio.models.FacebookUser;
import br.com.desafio.persistence.FacebookUserDAO;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.logging.Logger;

/**
 * Classe que representa os serviços de inclusão e listagem de usuarios
 */
public class FacebookUserServices extends ServiceBase {
    Logger log  = Logger.getLogger(FacebookUserServices.class.getName());
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
        log.info("requisição para listagem de usuarios");
        //pegando o parametro limite
        int limite = 0;
        if(request.queryParams("limit") != null){
            try{
                limite = Integer.parseInt(request.queryParams("limit"));
            }catch (NumberFormatException ex){
                log.info("Não é possivel parsear o atributo limit");
                log.info("retornando 400");
                response.status(400);
                return "Incorrect limit format";
            }
        }
        response.status(200);//desnecessario, mas só pra deixar claro
        FacebookUserDAO dao = new FacebookUserDAO();
        List<FacebookUser> list = dao.lista(limite);
        Gson gson = new Gson();
        log.info(String.format("retornando %d usuarios",list.size()));
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
        log.info("Chamada do serviço de inclusão de usuario");
        //confere se o facebookId foi passado como parametro
        if(request.queryParams("facebookId") == null){
            log.info("parametro facebook não foi passado");
            log.info("retornando http status 400");
            response.status(400);
            return "This request require a facebookId";
        }
        long facebookId = 0;
        try{
            facebookId = Long.parseLong(request.queryParams("facebookId"));
        }catch (NumberFormatException ex){
            log.info("parametro facebook não pode ser parseado");
            log.info("retornando http status 400");
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
            log.info(String.format("usuario com id %d foi salvo",facebookId));
            return "User saved";
        }else{
            log.info("Usuario não encontrado no facebook");
            log.info("retornando http status 400");
            response.status(400);
            return "Invalid facebook Id";
        }
    }

}
