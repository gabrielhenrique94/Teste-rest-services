package br.com.desafio.teste;

import br.com.desafio.httpClient.DesafioClient;
import br.com.desafio.httpClient.GraphClient;
import br.com.desafio.httpClient.RespostaHttp;
import br.com.desafio.models.FacebookUser;
import br.com.desafio.persistence.FacebookUserDAO;
import br.com.desafio.persistence.HibernateUtils;
import br.com.desafio.services.Services;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.logging.Logger;

/**
 * Classe que testa o serviço de deleção de usuario
 */
public class DeleteUsuarioTest extends TestCase {
    Logger log = Logger.getLogger(DeleteUsuarioTest.class.getName());
    /**
     * Teste que verifica se uma requisição feita corretamente realmente funciona
     */
    @Test
    public void testDelecao(){
        log.info("Testando deleção de usuario");
        Services.initServices();
        long id = 100001333447399L;
        //insere usuario no banco
        FacebookUserDAO dao = new FacebookUserDAO();
        GraphClient graphClient = new GraphClient();
        HibernateUtils.getEntityManager().getTransaction().begin();
        dao.salvar(graphClient.getFacebookUserById(id));
        HibernateUtils.getEntityManager().getTransaction().commit();
        HibernateUtils.closeEntityManager();
        //chama o serviço
        DesafioClient client = new DesafioClient();
        RespostaHttp resposta = client.removeUser(String.valueOf(id));
        //verifica o código de status
        assertEquals(resposta.getStatus(), 204);
        // verifica se o usuario realmente foi deletado
        FacebookUser user = dao.procuraPorId(id);
        assertNull("Usuario não pode estar na base",user);
    }

    /**
     * testa se a requisição realmente falha com um facebookId não numerico
     */
    @Test
    public void testFacebookIdNaoNumerico(){
        log.info("Testando deleção de usuario para ocaso de o facebookId estar errado");
        Services.initServices();
        String id = "is_not_id";
        DesafioClient client = new DesafioClient();
        RespostaHttp respostaHttp = client.removeUser(id);
        assertEquals(respostaHttp.getStatus(), 400);
        assertEquals(respostaHttp.getConteudo(), "Incorrect facebookId format");
    }

}
