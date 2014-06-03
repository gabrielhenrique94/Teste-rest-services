package br.com.desafio.httpClient;

import com.google.gson.Gson;
import br.com.desafio.dto.FacebookUserDTO;
import br.com.desafio.models.FacebookUser;

import java.util.LinkedList;

/**
 * Cliente Http para a Graph API do facebook
 */
public class GraphClient extends ClienteHttp{
    /**
     * Método que pega informações de um usuario usando a Graph API
     * @param id facebook id do usuario
     * @return Objeto Facebook user com as informações do usuario , ou null caso o usuario não exista
     */
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
