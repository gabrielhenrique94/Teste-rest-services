package services;

import spark.Request;
import spark.Response;

/**
 * Interface que representa um serviço
 */
public interface Service {
    /**
     * Método que indica o que deve ser chamado uma requisição do tipo get for recebida
     * @param request Objeto que representa a requisição @see spark.Request
     * @param response Objeto que representa a resposta @see spark.Response
     * @return  texto que será enviado como resposta da requisição
     */
    public String get(Request request, Response response);

    /**
     * Método que indica o que deve ser chamado uma requisição do tipo post for recebida
     * @param request Objeto que representa a requisição @see spark.Request
     * @param response Objeto que representa a resposta @see spark.Response
     * @return String texto que será enviado como resposta da requisição
     */
    public String post(Request request, Response response);

    /**
     * Método que indica o que deve ser chamado uma requisição do tipo put for recebida
     * @param request Objeto que representa a requisição @see spark.Request
     * @param response Objeto que representa a resposta @see spark.Response
     * @return  texto que será enviado como resposta da requisição
     */
    public String put(Request request, Response response);

    /**
     * Método que indica o que deve ser chamado uma requisição do tipo patch for recebida
     * @param request Objeto que representa a requisição @see spark.Request
     * @param response Objeto que representa a resposta @see spark.Response
     * @return  texto que será enviado como resposta da requisição
     */
    public String patch(Request request, Response response);

    /**
     * Método que indica o que deve ser chamado uma requisição do tipo delete for recebida
     * @param request Objeto que representa a requisição @see spark.Request
     * @param response Objeto que representa a resposta @see spark.Response
     * @return  texto que será enviado como resposta da requisição
     */
    public String delete(Request request, Response response);
}
