package Managers;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    public Logger logger;
    private FileHandler fh;

    public Log(String fileName) throws SecurityException, IOException {
        File f = new File(fileName);
        if (!f.exists()) {
            f.createNewFile();
        }

        fh = new FileHandler(fileName, true);
        logger = Logger.getLogger("Log");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
    }
}