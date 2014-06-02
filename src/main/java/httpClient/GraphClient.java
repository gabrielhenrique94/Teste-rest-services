package httpClient;

import com.google.gson.Gson;
import dto.FacebookUserDTO;
import models.FacebookUser;

import java.util.LinkedList;

/**
 * Cliente Http para a Graph API do facebook
 */
public class GraphClient extends ClienteHttp{
    public FacebookUser getFacebookUserById(Long id){
        String url = String.format("http://graph.facebook.com/%d", id);
        //não precisa de nenhum parametro
        RespostaHttp resposta = this.get(url, new LinkedList<Parametro>());
        //verifica se o usuario existe ou se a requisição foi feita corretamente
        if(resposta.getStatus() != 200){
            return null;
        }
        Gson gson = new Gson();
        FacebookUserDTO user = gson.fromJson(resposta.getConteudo(), FacebookUserDTO.class);
        return user.toFacebookUser();
    }
}
