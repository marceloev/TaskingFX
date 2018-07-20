package com.taskingfx.util.log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.net.URL;

public class TaskingLog {

    private static final Logger logger = Logger.getLogger(TaskingLog.class);
    private static final URL urlProperties = TaskingLog.class.getResource("/statics/properties/log4j.properties");
    private static Boolean configurado = false;

    public static void gravaInfo(Class invoker, String info) {
        if (isConfigurado())
            logger.info("[" + invoker.getName() + "] : " + info);
    }

    public static void gravaInfo(Class invoker, String info, String tips) {
        if (isConfigurado()) {
            logger.info("[" + invoker.getName() + "] : " + info);
            logger.info("[" + invoker.getName() + "] : " + tips);
        }
    }

    public static void gravaAlerta(Class invoker, String info) {
        if (isConfigurado())
            logger.warn("[" + invoker.getName() + "] : " + info);
    }

    public static void gravaAlerta(Class invoker, String info, String tips) {
        if (isConfigurado()) {
            logger.warn("[" + invoker.getName() + "] : " + info);
            logger.warn("[" + invoker.getName() + "] : " + tips);
        }
    }

    public static void gravaErro(Class invoker, String info) {
        if (isConfigurado())
            logger.error("[" + invoker.getName() + "] : " + info);
    }

    public static void gravaErro(Class invoker, String info, Throwable trhows) {
        if (isConfigurado())
            logger.error("[" + invoker.getName() + "] : " + info, trhows);
    }

    private static Boolean isConfigurado() {
        if (!configurado)
            PropertyConfigurator.configure(urlProperties);
        configurado = true;
        return true;
    }
}
