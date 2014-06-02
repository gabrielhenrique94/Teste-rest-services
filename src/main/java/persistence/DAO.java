package persistence;

import java.util.List;

/**
 * Interface que define os métodos de persistencia com o banco
 */
public interface DAO<T> {
    /**
     * Persiste o objeto. salva quando ele não existe , ou atualiza quando ele existe
     * @param obj objeto a ser salvo ou atualizado
     * @return o objeto como foi retornado para o sistema
     */
    public T salvar(T obj);

    /**
     * remove o objeto do banco.
     * @param obj objeto que deve ser removido do banco.
     */
    public void deletar(T obj);

    /**
     * Lista os objetos que estão no banco
     * @param limit numero maximo de objetos a serem retornados, caso limit seja menor ou igual a 0 , todos os objetos serão retornados
     * @return retorna uma lista com o tamanho maximo passado por parametro, dos objetos que estão salvos
     */
    public List<T> lista(int limit);
}
