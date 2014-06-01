package persistence;

import org.hibernate.ejb.EntityManagerFactoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe que implementa utilidades relacionadas ao hibernate
 */
public class HibernateUtils {
    //Objeto que guarda a lista de entity managers, por thread. simula o requestScope
    private static ThreadLocal<EntityManager> entityManagerList = new ThreadLocal<EntityManager>();
    //factory do
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("facebook_user");

    /**
     * Método que cria um EntityManager, associado à Thread que o chama.
     * @return retorna um EntityManager
     */
    public static EntityManager getEntityManager(){
        //Como cada requisição roda em uma Thread,
        //essa collection me da alguma garantia de que o EntityManager vai ser unico para cada requisição
        EntityManager em = entityManagerList.get();
        if(em != null) {
            em = factory.createEntityManager();
            entityManagerList.set(em);
        }
        return em;
    }

    /**
     * Método responsavel por fechar o entity manager
     */
    public static void closeEntityManager(){
        getEntityManager().close();
        entityManagerList.remove();
    }
}
