package persistence;

import models.FacebookUser;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class FacebookUserDAO implements DAO<FacebookUser> {
    /**
     * {@inheritDoc}
     */
    @Override
    public FacebookUser salvar(FacebookUser obj) {
        return HibernateUtils.getEntityManager().merge(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deletar(FacebookUser obj) {
        HibernateUtils.getEntityManager().remove(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FacebookUser> lista(int limit) {
        Criteria criteria = criaCriteria();
        if(limit > 0) {
            criteria.setMaxResults(limit);
        }
        return criteria.list();
    }

    /**
     * Método que localiza um usuario pelo Id
     * @param facebookId id do usuario
     * @return Usuario com o id especificado , ou null no caso dele não existir
     */
    public FacebookUser procuraPorId(int facebookId){
        Criteria criteria = criaCriteria();
        criteria.add(Restrictions.eq("facebookId", facebookId));
        List<FacebookUser> busca = criteria.list();
        return busca.get(0);
    }

    /**
     * Método que centraliza a criação de criterios para busca
     * @return novo criteria.
     */
    protected Criteria criaCriteria() {
        Session session = (Session) HibernateUtils.getEntityManager().getDelegate();
        Criteria criteria = session.createCriteria(FacebookUser.class);
        return criteria;
    }

}
