package br.com.desafio.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Cliente Http para ser usado em alguma sub-classe
 */
public abstract class ClienteHttp {
    /**
     * Executa uma requisição http do tipo GET
     * @param url Url onde será feita a requisição ex: http://www.google.com
     * @param parametros Lista com os parametros a serem colocados na URL
     * @return  um objeto contendo o conteudo da reposta e o código de status da resposta
     */
    protected RespostaHttp get(String url, List<Parametro> parametros) {
        url = url + '?' + URLEncodedUtils.format(parametros, "UTF-8");
        HttpUriRequest request = new HttpGet(url);
        return executeHttpRequest(request);
    }

    /**
     * Executa uma requisição http do tipo POST
     * @param url Url onde será feita a requisição ex: http://www.google.com
     * @param parametros Lista com os parametros a serem colocados na URL
     * @return  um objeto contendo o conteudo da reposta e o código de status da resposta
     */
    protected RespostaHttp post(String url, List<Parametro> parametros) {
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(parametros,
                    "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpPost request = new HttpPost(url);
        request.setEntity(entity);
        return executeHttpRequest(request);
    }

    /**
     * Executa uma requisição http do tipo DELETE
     * @param url Url onde será feita a requisição ex: http://www.google.com
     * @param parametros Lista com os parametros a serem colocados na URL
     * @return  um objeto contendo o conteudo da reposta e o código de status da resposta
     */
    protected RespostaHttp delete(String url, List<Parametro> parametros) {
        url = url + '?' + URLEncodedUtils.format(parametros, "UTF-8");
        HttpUriRequest request = new HttpDelete(url);
        return executeHttpRequest(request);
    }

    /**
     * Método que executa uma request
     * @param request requisição previamente configurada
     * @return RespostaHttp objeto que representa a resposat obtida do servidor
     * @see org.apache.http.client.methods.HttpUriRequest
     */
    protected static RespostaHttp executeHttpRequest(HttpUriRequest request){
        RespostaHttp resposta = new RespostaHttp();
        HttpClient cliente = new DefaultHttpClient();
        HttpResponse response;
        ByteArrayOutputStream output = null;
        try {
            //faz a chamada
            response = cliente.execute(request);
            //pega os bytes de resposta
            HttpEntity entity = response.getEntity();
            String stringResposta;
            if(entity != null){//se a reposta tem corpo
                output = new ByteArrayOutputStream();
                entity.writeTo(output);
                byte[] byteArray = output.toByteArray();
                //transforma os bytes em string
               stringResposta = new String(byteArray);
            }else{
                stringResposta = "";
            }
            //coloca o conteudo e o código da resposta no objeto de resposta
            resposta.setConteudo(stringResposta);
            resposta.setStatus(response.getStatusLine().getStatusCode());
            return resposta;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            //fecha o outPutStream
            try {
                output.close();
            } catch (Exception e) {
                // não fecha se não estiver aberto ou se for null
            }
        }
    }
}