package services.filters;

import persistence.HibernateUtils;
import spark.Filter;
import spark.Request;
import spark.Response;

/**
 * Filtro do spark responsavel por abrir uma transação do hibernate
 */
class BeginTransactionFilter extends Filter {

    @Override
    /**
     * {{@inheritDoc}}
     */
    public void handle(Request request, Response response) {
        HibernateUtils.getEntityManager().getTransaction().begin();
    }
}