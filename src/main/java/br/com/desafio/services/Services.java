package br.com.desafio.services;

import br.com.desafio.services.filters.CommitTransactionFilter;
import br.com.desafio.services.filters.BeginTransactionFilter;
import spark.Request;
import spark.Response;
import spark.Route;

import spark.Spark;
public class Services {
    private static boolean isRunning = false;
    /**
     * Método que inicia os serviços rest e os filtros
     * Ele tambem inicia o servidor spark
     */
    public static void initServices(){
        if(!isRunning) {
            Spark.setPort(8080);
            //inicia os filtros que abrem e fecham a transação do hibernate
            Spark.before(new BeginTransactionFilter());
            Spark.after(new CommitTransactionFilter());
            //Linka os serviços ao spark
            addServices(new DeleteUserService(), "/person/:facebookId/");
            addServices(new FacebookUserServices(), "/person/");
            isRunning = true;
        }
    }

    /**
     * Método que para os serviços, e o servidor Spark
     */
    public static void stopServices(){
        Spark.stop();
    }
    /**
     * Adiciona um serviço ao servidor, na URI passada como parametro em route,
     * @param service serviço que será adicionado
     * @param route URI onde o serviço estará disponivel, para receber um parametro pela URI use /example/:param_name
     */
    public static void addServices(final Service service, String route) {
        //Linkando cada método do serviço ao spark;
        Spark.get(route,new Route() {
            @Override
            public Object handle(Request req, Response res) {
                return service.get(req, res);
            }
        });
        Spark.post(route,new Route() {
            @Override
            public Object handle(Request req, Response res) {
                return service.post(req, res);
            }
        });
        Spark.put(route,new Route() {
            @Override
            public Object handle(Request req, Response res) {
                return service.put(req, res);
            }
        });
        Spark.delete(route,new Route() {
            @Override
            public Object handle(Request req, Response res) {
                return service.delete(req, res);
            }
        });
        Spark.patch(route,new Route() {
            @Override
            public Object handle(Request req, Response res) {
                return service.patch(req, res);
            }
        });
    }

}
