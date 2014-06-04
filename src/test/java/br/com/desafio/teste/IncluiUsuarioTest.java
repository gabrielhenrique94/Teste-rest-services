package br.com.desafio.teste;

import br.com.desafio.httpClient.RespostaHttp;
import br.com.desafio.models.FacebookUser;
import br.com.desafio.persistence.FacebookUserDAO;
import br.com.desafio.persistence.HibernateUtils;
import br.com.desafio.services.Services;
import junit.framework.TestCase;
import br.com.desafio.httpClient.DesafioClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;

/**
 * Classe que testa os WebServices do desafio
 */
public class IncluiUsuarioTest extends TestCase{


    /**
     * Testa se uma requisição feita corretamente realmente funciona
     */
    @Test
    public void testInsercao(){
        Services.initServices();
        long id = 100001333447399L;
        //chama o serviço
        DesafioClient client = new DesafioClient();
        RespostaHttp resposta = client.insereUser(String.valueOf(id));
        //verifica o código de status
        assertEquals(resposta.getStatus(), 201);
        assertEquals(resposta.getConteudo(), "User saved");
        // verifica se o usuario realmente está no banco
        FacebookUserDAO dao = new FacebookUserDAO();
        FacebookUser user = dao.procuraPorId(id);
        assertNotNull(user);
        //remove o usuario da base(limpa a sujeira)
        HibernateUtils.getEntityManager().getTransaction().begin();
        dao.deletar(user);
        HibernateUtils.getEntityManager().getTransaction().commit();
        HibernateUtils.closeEntityManager();
    }

    /**
     * Testa pro caso de o facebookId não existir no facebook,
     * ele deve retornar 400(Bad request), e a mensagem de erro apropriada
     */
    @Test
    public void testFacebookIdErrado(){
        Services.initServices();
        long id = 0;
        //chama o serviço
        DesafioClient client = new DesafioClient();
        RespostaHttp resposta = client.insereUser(String.valueOf(id));
        assertEquals(resposta.getStatus(), 400);
        assertEquals(resposta.getConteudo(), "Invalid facebook Id");
    }
    /**
     * Testa para o caso de não passar um id para o server
     * ele deve retornar 400(Bad request), e a mensagem de erro apropriada
     */
    @Test
    public void testSemFacebookId(){
        Services.initServices();
        DesafioClient client = new DesafioClient();
        //chama o serviço
        //null aqui porque a ideia é não passar um facebookId
        RespostaHttp respostaHttp = client.insereUser(null);
        assertEquals(respostaHttp.getStatus(),400);
        assertEquals(respostaHttp.getConteudo(), "This request require a facebookId");
    }

    /**
     * testa se a requisição realmente falha com um facebookId não numerico
     */
    @Test
    public void testFacebookIdNaoNumerico(){
        Services.initServices();
        String id = "this isn't an Id";
        DesafioClient client = new DesafioClient();
        RespostaHttp respostaHttp = client.insereUser(id);
        assertEquals(respostaHttp.getStatus(), 400);
        assertEquals(respostaHttp.getConteudo(), "Incorrect facebookId format");
    }
}
