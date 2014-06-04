package br.com.desafio.services.filters;

import br.com.desafio.persistence.HibernateUtils;
import spark.Filter;
import spark.Request;
import spark.Response;

/**
 * Filtro do spark responsavel por abrir uma transação do hibernate
 */
public class BeginTransactionFilter implements Filter {

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(Request request, Response response) {
        HibernateUtils.getEntityManager().getTransaction().begin();
    }
}