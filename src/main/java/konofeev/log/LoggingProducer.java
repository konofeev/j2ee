package konofeev.log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Производитель объекта журналирования (логгирования)
 */
public class LoggingProducer
{

    /**
     * Конструктор
     *
     * @param injectionPoint Точка внедрения
     */
    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint)
    {
        Logger logger = Logger.getLogger
        (
            injectionPoint.getMember().getDeclaringClass().getName()
        );

        FileHandler fileHandler;

        try
        {
            final String LOG_HOME = System.getenv("LOG_HOME");
            fileHandler = new FileHandler(LOG_HOME + "game.log", true);
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return logger;
    }

}
