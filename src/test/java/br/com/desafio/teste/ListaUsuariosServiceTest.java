package br.com.desafio.teste;

import br.com.desafio.httpClient.DesafioClient;
import br.com.desafio.httpClient.GraphClient;
import br.com.desafio.httpClient.RespostaHttp;
import br.com.desafio.models.FacebookUser;
import br.com.desafio.persistence.FacebookUserDAO;
import br.com.desafio.persistence.HibernateUtils;
import br.com.desafio.services.Services;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import static junit.framework.TestCase.*;
/**
 * Classe que testa os serviços de listagem
 */
public class ListaUsuariosServiceTest {
    private static long[] ID = {100001333447399L, 100008143294726L, 1539713739L, 100001227745856L};
    private static FacebookUserDAO dao;
    private static GraphClient graphClient;
    private static DesafioClient client;
    private static Gson gson;

    public static void init(){
        //popula base para testes
        HibernateUtils.getEntityManager().getTransaction().begin();
        dao = new FacebookUserDAO();
        graphClient = new GraphClient();
        client = new DesafioClient();
        gson = new Gson();
        for(long id : ID){
            dao.salvar(graphClient.getFacebookUserById(id));
        }
        HibernateUtils.getEntityManager().getTransaction().commit();
        HibernateUtils.closeEntityManager();
        //inicia o Spark
        Services.initServices();
    }

    /**
     * Teste que verifica se o listar todos funciona.
     */
    @Test
    public void testaListaTodos(){
        init();
        RespostaHttp respostaHttp = client.listaUsers();
        assertEquals(respostaHttp.getStatus(), 200);
        List<FacebookUser> userList = gson.fromJson(respostaHttp.getConteudo(), new TypeToken<List<FacebookUser>>(){}.getType());
        assertTrue(userList.size() >= ID.length);

        for(long id : ID){
            FacebookUser user = new FacebookUser();
            user.setFacebookId(id);
            assertTrue("A lista deve conter pelo menos os ids inseridos no inicio dos testes no banco", userList.contains(user));
        }
    }

    /**
     * Teste que verifica se a requisição não execede o limite
     */
    @Test
    public void testaListaLimite(){
        init();
        int limit = 2;
        RespostaHttp respostaHttp = client.listaUsers(String.valueOf(limit));
        assertEquals(respostaHttp.getStatus(), 200);
        List<FacebookUser> userList = gson.fromJson(respostaHttp.getConteudo(), new TypeToken<List<FacebookUser>>(){}.getType());
        assertTrue("Lista não pode ter mais elementos do que o limite especificado Elementos", userList.size() <= limit);
    }

    /**
     * Teste que verifica se a requisição retorna erro com limite errado
     */
    @Test
    public void testaListaLimiteErrado(){
        init();
        String limit = "nao é um numero";
        RespostaHttp respostaHttp = client.listaUsers(limit);
        assertEquals(respostaHttp.getStatus(), 400);
        assertEquals(respostaHttp.getConteudo(),"Incorrect limit format");
    }
}
