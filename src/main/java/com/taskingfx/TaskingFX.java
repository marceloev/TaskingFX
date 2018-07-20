package com.taskingfx;

import com.taskingfx.DAO.UserDAO;
import com.taskingfx.entitys.User;

public class TaskingFX {

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findByPK(1);
        System.out.println(user.toString());
    }
}
