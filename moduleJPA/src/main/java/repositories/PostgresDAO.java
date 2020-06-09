package repositories;

import dao.impl.AbstractGenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

public abstract class PostgresDAO<T, ID extends Serializable> extends AbstractGenericDao<T, ID> {

    private static final String PERSISTENCE_UNIT_NAME = "managedPersistenceUnitPostgresql";

    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
    private EntityManager em;

    public PostgresDAO(Class<T> type) {
        super(type);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
