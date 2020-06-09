package dao.interceptors;

import dao.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * DAO interceptor intended for better logging
 */
public class DAOInterceptor {

    final private static Logger logger = LoggerFactory.getLogger(DAOInterceptor.class);

    static private void log(InvocationContext context) {
        String targetClass = context.getTarget().getClass().getCanonicalName();
        String methodName = context.getMethod().getName();
        logger.error("Exception calling DAO " + targetClass + ", method " + methodName + buildParameters(context));
    }

    static private String buildParameters(InvocationContext context) {
        if (context.getParameters() == null) {
            return " with no parameters";
        }
        if (context.getParameters().length == 0) {
            return " with no parameters";
        }
        StringBuilder builder = new StringBuilder(" with parameters { ");
        for (int i = 0; i < context.getParameters().length; i++) {
            try {
                Object object = context.getParameters()[i];
                builder.append(String.valueOf(object));
            } catch (Exception e) {
                builder.append("N/A");
            }
            if (i < context.getParameters().length - 1) {
                builder.append(" , ");
            }
        }
        builder.append(" }");
        return builder.toString();
    }

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        try {
            return context.proceed();
        } catch (Exception e) {
            log(context);
            if (e instanceof DAOException) {
                throw e;
            }
            throw new DAOException(e);
        }
    }

}
