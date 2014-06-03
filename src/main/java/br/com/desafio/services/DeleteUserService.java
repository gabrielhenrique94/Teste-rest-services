package br.com.desafio.services;

import br.com.desafio.dto.FacebookUserDTO;
import br.com.desafio.httpClient.GraphClient;
import br.com.desafio.models.FacebookUser;
import br.com.desafio.persistence.FacebookUserDAO;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.List;

/**
 * Classe que implementa o serviço de deleção de usuario
 */
public class DeleteUserService extends ServiceBase {
    @Override
    /**
     * remove o usuario com id passado como parametro da base local
     * Status 204: Usuario não está na base(foi deletado ou já não existia)
     * Status 400: facebookId não é um número
     */
    public String delete(Request request, Response response) {
        //facebookId não existe
        if(request.params("facebookId") == null){
            response.status(400);
            return "This request require a facebookId";
        }
        long facebookId = 0L;
        try{
            facebookId = Long.parseLong(request.params("facebookId"));
        }catch (NumberFormatException ex){
            response.status(400);
            return "Incorrect facebookId format";
        }
        FacebookUserDAO dao = new FacebookUserDAO();
        FacebookUser user = dao.procuraPorId(facebookId);
        //checa se o usuario existe ou não
        if(user != null) {
            dao.deletar(user);
        }
            response.status(204);
            return "";
    }
}
