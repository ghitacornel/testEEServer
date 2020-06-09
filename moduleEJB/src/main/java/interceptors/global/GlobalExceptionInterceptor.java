package interceptors.global;

import dao.exceptions.DAOException;
import exceptions.BusinessException;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.PersistenceException;

@Interceptor
public class GlobalExceptionInterceptor {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        try {
            return context.proceed();
        } catch (Exception e) {

            if (e instanceof DAOException) {
                throw e;
            }
            if (e instanceof PersistenceException) {
                throw e;
            }

            if (e instanceof BusinessException) {
                throw e;
            }

            throw new BusinessException(e);
        }
    }

}
