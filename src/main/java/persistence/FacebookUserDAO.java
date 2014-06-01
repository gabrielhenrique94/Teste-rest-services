package persistence;

import models.FacebookUser;
import org.hibernate.Criteria;
import org.hibernate.Session;

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
     * Método que centraliza a criação de criterios para busca
     * @return novo criteria.
     */
    protected Criteria criaCriteria() {
        Session session = (Session) HibernateUtils.getEntityManager().getDelegate();
        Criteria criteria = session.createCriteria(FacebookUser.class);
        return criteria;
    }

}
