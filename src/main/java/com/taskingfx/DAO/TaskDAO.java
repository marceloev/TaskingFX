package com.taskingfx.DAO;

import com.taskingfx.entitys.Task;

import javax.persistence.Query;
import java.util.List;

public class TaskDAO extends GenericDAO<Task, Integer> {

    public TaskDAO() {
        super(Task.class);
    }

    public List<Task> findAll(int codigoUsuario) {
        Query query = this.getEntityManager().createNamedQuery("Task.findAll");
        query.setParameter("P_CODUSU", codigoUsuario);
        return query.getResultList();
    }

    public List<Task> findPendentes(int codigoUsuario) {
        Query query = this.getEntityManager().createNamedQuery("Task.findPendentes");
        query.setParameter("CODUSU", codigoUsuario);
        return query.getResultList();
    }
}
