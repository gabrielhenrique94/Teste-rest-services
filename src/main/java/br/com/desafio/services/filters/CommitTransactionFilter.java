package br.com.desafio.services.filters;

import br.com.desafio.persistence.HibernateUtils;
import spark.Filter;
import spark.Request;
import spark.Response;

/**
 * Filtro responsavel por aplicar no banco as modificações feitas durante o tempo de duração da request
 * e fechar o entity manager
 */
public class CommitTransactionFilter implements Filter {

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(Request request, Response response) {
        HibernateUtils.getEntityManager().getTransaction().commit();
        HibernateUtils.closeEntityManager();
    }
}
