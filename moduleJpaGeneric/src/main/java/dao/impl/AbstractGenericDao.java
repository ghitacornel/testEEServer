package dao.impl;


import dao.GenericDao;
import dao.exceptions.DAOEntityNotFoundException;
import dao.interceptors.DAOInterceptor;

import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Interceptors(DAOInterceptor.class)
public abstract class AbstractGenericDao<Entity, ID extends Serializable> implements GenericDao<Entity, ID> {

    // need to have it for later use
    final private Class<Entity> type;

    public AbstractGenericDao(Class<Entity> type) {
        this.type = type;
    }

    protected abstract EntityManager getEntityManager();

    private String getEntityName() {
        return getEntityManager().getMetamodel().entity(type).getName();
    }

    @Override
    public void persist(Entity entity) {
        getEntityManager().persist(entity);
    }

    @Override
    public void merge(Entity entity) {
        getEntityManager().merge(entity);
    }

    @Override
    public void remove(Entity entity) {
        getEntityManager().remove(entity);
    }

    @Override
    public void detach(Entity entity) {
        getEntityManager().detach(entity);
    }

    @Override
    public void refresh(Entity entity) {
        getEntityManager().refresh(entity);
    }

    @Override
    public void flush() {
        getEntityManager().flush();
    }

    @Override
    public List<Entity> findAll() {
        return getEntityManager().createQuery("select t from " + getEntityName() + " t", type).getResultList();
    }

    @Override
    public Entity findById(ID id) {
        return getEntityManager().find(type, id);
    }

    @Override
    public Entity getById(ID id) {
        Entity entity = getEntityManager().find(type, id);
        if (entity == null) throw new DAOEntityNotFoundException(type.getCanonicalName(), id);
        return entity;
    }

    @Override
    public List<Entity> findByIds(Collection<ID> ids) {
        if (ids == null) return new ArrayList<>();
        if (ids.isEmpty()) return new ArrayList<>();
        return getEntityManager().createQuery("select t from " + getEntityName() + " t where t.id in :ids", type)
                .setParameter("ids", ids)
                .getResultList();
    }

}
