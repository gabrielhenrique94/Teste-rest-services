package models;

import javax.persistence.*;
import java.security.InvalidParameterException;

/**
 * Classe que representa o usuario do facebook dentro do sistema
 */

@Entity
@Table
public class FacebookUser {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "facebook_id")
    private Long facebookId;

    @Column(name = "nome")
    private String nome;

    @Column(name = "username")
    private String username;

    @Column(name = "genero")
    private String genero;

    //getters e setters.

    /**
     * Método que retorna o id interno do usuario
     * @return Long retorna o id do usuario;
     */
    public Long getId() {
        return id;
    }

    /**
     * Método que retorna o id do usuario no facebook
     * @return Long retorna o id do usuario
     */
    public Long getFacebookId() {
        return facebookId;
    }

    /**
     * Método que retorna o nome do usuario
     * @return String string com o nome do usuario
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método que retorna uma string que representa o genero da pessoa
     * male para masculino e female para feminino
     * @return String devolve string com o gênero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Método que altera o facebook id do usuario
     * @param facebookId valor a ser colocado no faebook id do objeto
     */
    public void setFacebookId(Long facebookId) {
        this.facebookId = facebookId;
    }

    /**
     * Método que altera o atributo nome do usuario
     * @param nome nome que deve ser colocado no atributo nome do usuario
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método que altera o atributo Username do usuario
     * @param username novo username do usuario
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Método que altera o atributo genero do usuario
     * @param genero String que contem o genero do usuario , deve obrigatoriamente ter um desses valores:
     *               "male", "female", "M" ou "F"
     * @throws InvalidParameterException se o parametro genero não contiver um dos valores especificados
     */
    public void setGenero(String genero) {
        if (genero.equals("male") || genero.equals("female")) {
            this.genero = genero;
        } else if (genero.equals("M")) {
            this.genero = "male";
        } else if (genero.equals("F")) {
            this.genero = "female";
        } else {
            throw new InvalidParameterException("Parametro genero invalido");
        }
    }
}
