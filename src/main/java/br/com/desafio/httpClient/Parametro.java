package br.com.desafio.httpClient;

import org.apache.http.NameValuePair;

/**
 * Classe que representa um parametro http
 */
public class Parametro implements NameValuePair {
    private String name;
    private String value;

    /**
     * Construtor padrão da classe
     * @param name nome do parametro
     * @param value valor do parametro
     */
    public Parametro(String name , String value){
        this.name = name;
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getValue() {
        return value;
    }
}
