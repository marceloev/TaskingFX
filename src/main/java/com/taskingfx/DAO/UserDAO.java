package com.taskingfx.DAO;

import com.taskingfx.entitys.User;

public class UserDAO extends GenericDAO<User, Integer> {

    public UserDAO() {
        super(User.class);
    }
}
