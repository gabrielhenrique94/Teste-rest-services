package services;

import services.filters.CommitTransactionFilter;
import services.filters.BeginTransactionFilter;
import spark.Request;
import spark.Response;
import spark.Route;

import spark.Spark;

public class Services {
    /**
     * Método que inicia os serviços rest e os filtros
     */
    public static void initServices(){
        //inicia os filtros que abrem e fecham a transação do hibernate
        Spark.before(new BeginTransactionFilter());
        Spark.after(new CommitTransactionFilter());
    }

    /**
     * Adiciona um serviço ao servidor, na URI passada como parametro em route,
     * @param service serviço que será adicionado
     * @param route URI onde o serviço estará disponivel, para receber um parametro pela URI use /example/:param_name
     */
    public static void addServices(final Service service, String route) {
        //Linkando cada método do serviço ao spark;
        Spark.get(new Route(route) {
            @Override
            public Object handle(Request req, Response res) {
                return service.get(req, res);
            }
        });
        Spark.post(new Route(route) {
            @Override
            public Object handle(Request req, Response res) {
                return service.post(req, res);
            }
        });
        Spark.put(new Route(route) {
            @Override
            public Object handle(Request req, Response res) {
                return service.put(req, res);
            }
        });
        Spark.delete(new Route(route) {
            @Override
            public Object handle(Request req, Response res) {
                return service.delete(req, res);
            }
        });
        Spark.patch(new Route(route) {
            @Override
            public Object handle(Request req, Response res) {
                return service.patch(req, res);
            }
        });
    }
}
