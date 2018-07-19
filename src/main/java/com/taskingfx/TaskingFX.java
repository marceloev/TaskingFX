package com.taskingfx;

import java.net.URL;

public class TaskingFX {

    public static void main(String[] args) {
        URL url = TaskingFX.class.getResource("util/log/");
        System.out.println(url);
        //GravaLog.gravaInfo(TaskingFX.class, user.toString());
    }
}
