package services;

import spark.Request;
import spark.Response;


/**
 * Implementação abstrata de um serviço, o objetivo dessa classe é criar um adapter,
 * para implementar apenas os métodos que realmente serão usados
 */
public abstract class ServiceBase implements Service {

    /**
     * Método que indica o que fazer quando o recurso buscado não existe
     * @param request  Objeto que representa a requisição
     * @param response Objeto que representa a resposta
     * @return padrão para não encontrado. Sempre retorna "Not Found"
     */
    protected String notFound(Request request, Response response) {
        response.status(404); // 404 Not found
        return "Not Found";
    }

    /**
     * Método que indica a reposta padrão para um método HTTP não implementado
     * @param request  Objeto que representa a requisição
     * @param response Objeto que representa a resposta
     * @return String padrão para método não implementado. Sempre retorna "Method not allowed
     */
    protected String notAllowed(Request request, Response response) {
        response.status(405);
        return "Method not allowed";
    }

    /**
     * {@inheritDoc}
     */
    public String get(Request request, Response response) {
        return notAllowed(request, response);
    }

    /**
     * {@inheritDoc}
     */
    public String post(Request request, Response response) {
        return notAllowed(request, response);
    }

    /**
     * {@inheritDoc}
     */
    public String put(Request request, Response response) {
        return notAllowed(request, response);
    }


    /**
     * {@inheritDoc}
     */
    public String patch(Request request, Response response) {
        return notAllowed(request, response);
    }

    /**
     * {@inheritDoc}
     */
    public String delete(Request request, Response response) {
        return notAllowed(request, response);
    }

}