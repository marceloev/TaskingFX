package com.taskingfx.DAO;

import com.taskingfx.entitys.User;
import com.taskingfx.model.dialogs.ModelDialog;
import com.taskingfx.model.dialogs.ModelDialogType;

import javax.persistence.NoResultException;
import javax.persistence.Query;

public class UserDAO extends GenericDAO<User, Integer> {

    public UserDAO() {
        super(User.class);
    }

    public User authenticateUser(String login, String senha) {
        try {
            User user = findByLogin(login);
            if (user.getSenha().equals(senha))
                return user;
            else
                throw new NoResultException();
        } catch (NoResultException ex) {
            new ModelDialog(ModelDialogType.Alerta)
                    .show("Usuário/Senha inválidos");
        } catch (Exception ex) {
            new ModelDialog(ModelDialogType.Erro)
                    .show("Erro ao tentar autenticar usuário");
        }
        return null;
    }

    public User findByLogin(String login) throws NoResultException, Exception {
        Query query = this.getEntityManager().createNamedQuery("User.findByLogin");
        query.setParameter("P_LOGIN", login);
        return (User) query.getSingleResult();
    }
}
