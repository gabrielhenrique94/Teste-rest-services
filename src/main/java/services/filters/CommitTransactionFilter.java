package services.filters;

import persistence.HibernateUtils;
import spark.Filter;
import spark.Request;
import spark.Response;

/**
 * Created by gabriel on 01/06/2014.
 */
public class CommitTransactionFilter extends Filter {

    @Override
    public void handle(Request request, Response response) {
        HibernateUtils.getEntityManager().getTransaction().commit();
        HibernateUtils.closeEntityManager();
    }
}
