package dto;

import models.FacebookUser;

import java.security.InvalidParameterException;

/**
 * Classe que representa o usuario do facebook como ele vem do serviço
 * DTO = data transfer object
 */
public class FacebookUserDTO {
    private Long id;
    private String name;
    private String username;
    private String gender;

    /**
     * Método que retorna o id interno do usuario
     * @return Long retorna o id do usuario;
     */
    public Long getId() {
        return id;
    }

    /**
     * Método que retorna o nome do usuario
     * @return String string com o nome do usuario
     */
    public String getName() {
        return name;
    }

    /**
     * Método que retorna uma string que representa o gender da pessoa
     * male para masculino e female para feminino
     * @return String devolve string com o gênero
     */
    public String getgender() {
        return gender;
    }

    /**
     * Método que altera o facebook id do usuario
     * @param id valor a ser colocado no atributo id do objeto
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Método que altera o atributo nome do usuario
     * @param name nome que deve ser colocado no atributo nome do usuario
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Método que altera o atributo Username do usuario
     * @param username novo username do usuario
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Método que altera o atributo gender do usuario
     * @param gender String que contem o genero do usuario , deve obrigatoriamente ter um desses valores:
     *               "male", "female", "M" ou "F"
     * @throws java.security.InvalidParameterException se o parametro gender não contiver um dos valores especificados
     */
    public void setGender(String gender) {
        if (gender.equals("male") || gender.equals("female")) {
            this.gender = gender;
        } else if (gender.equals("M")) {
            this.gender = "male";
        } else if (gender.equals("F")) {
            this.gender = "female";
        } else {
            throw new InvalidParameterException("Parametro gender invalido");
        }
    }

    /**
     * Método que converte o FacebookUserDTO em FacebookUser
     * @return um facebookUser com os dados equivalentes desse objeto
     */
    public FacebookUser toFacebookUser(){
        FacebookUser user = new FacebookUser();
        user.setFacebookId(this.id);
        user.setGenero(this.gender);
        user.setNome(this.name);
        user.setUsername(this.username);
        return user;
    }
}
