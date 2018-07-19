package com.taskingfx.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class GenericDAO<Entidade, PK> {


    private final EntityManagerFactory factory;
    private final EntityManager entityManager;
    private Class<Entidade> persistedClass;

    public GenericDAO(Class<Entidade> entidade) {
        this.factory = Persistence.createEntityManagerFactory("TaskingFX");
        this.entityManager = factory.createEntityManager();
        this.persistedClass = entidade;
    }

    public GenericDAO(Class<Entidade> entidade, EntityManagerFactory factory) {
        this.factory = factory;
        this.entityManager = factory.createEntityManager();
        this.persistedClass = entidade;
    }

    public List<Entidade> findAll() {
        return this.entityManager
                .createQuery("FROM " + this.persistedClass.getName())
                .getResultList();
    }

    public Entidade findByPK(PK pk) {
        return (Entidade) this.entityManager.find(this.persistedClass, pk);
    }

    public void save(Entidade entidade) throws Exception {
        try {
            this.beginTransaction();
            this.entityManager.persist(entidade);
            this.commit();
        } catch (Exception ex) {
            this.rollBack();
            throw ex;
        }
    }

    public void update(Entidade entidade) throws Exception {
        try {
            this.beginTransaction();
            this.entityManager.merge(entidade);
            this.commit();
        } catch (Exception ex) {
            this.rollBack();
            throw ex;
        }
    }

    public void delete(Entidade entidade) throws Exception {
        try {
            this.beginTransaction();
            this.entityManager.remove(entidade);
            this.commit();
        } catch (Exception ex) {
            this.rollBack();
            throw ex;
        }
    }

    public void beginTransaction() {
        this.entityManager.getTransaction().begin();
    }

    public void commit() {
        this.entityManager.getTransaction().commit();
    }

    public void rollBack() {
        this.entityManager.getTransaction().rollback();
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }
}
