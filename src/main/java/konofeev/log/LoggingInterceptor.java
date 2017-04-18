package konofeev.log;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * Журналирование - перехватывание событий
 */
@Interceptor
@Loggable
public class LoggingInterceptor
{

    @Inject
    private Logger logger;

    /**
     * Перехватывание вызова методов
     */
    @AroundInvoke
    public Object logMethod(InvocationContext invocationContext)
        throws Exception
    {
        logger.entering
        (
            invocationContext.getTarget().getClass().getName(),
            invocationContext.getMethod().getName()
        );

        try
        {
            return invocationContext.proceed();
        }
        finally
        {
            logger.exiting
            (
                invocationContext.getTarget().getClass().getName(),
                invocationContext.getMethod().getName()
            );
        }
    }
}
