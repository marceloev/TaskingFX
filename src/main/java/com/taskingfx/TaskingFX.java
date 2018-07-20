package com.taskingfx;

import com.taskingfx.DAO.UserDAO;
import com.taskingfx.entitys.User;
import com.taskingfx.util.log.TaskingLog;

public class TaskingFX {

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findByPK(1);
        System.out.println(user.toString());
        TaskingLog.gravaInfo(TaskingFX.class, user.toString());
    }
}
