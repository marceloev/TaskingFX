package com.taskingfx;

import com.taskingfx.DAO.UserDAO;
import com.taskingfx.entitys.User;

public class TaskingFX {

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        User user = new User('S',
                "TESTE",
                "teste",
                "teste",
                "teste",
                "teste@email.com",
                null
        );
        //GravaLog.gravaInfo(TaskingFX.class, user.toString());
    }
}
