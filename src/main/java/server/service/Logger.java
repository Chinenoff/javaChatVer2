package server.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    protected int num = 1;
    private static Logger instance;

    public void log(String logMsg) {
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println("[" + formater.format(date) + " " + num++ + "] " + logMsg); //"[" + Thread.currentThread().getName() + "] " +
    }

    private static Logger logger;

    private Logger() {
    }

    public static Logger getInstance() {
        if (instance == null) instance = new Logger();
        return instance;
    }
}
