package interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import java.io.IOException;

public class RequestServerReaderInterceptor implements ReaderInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestServerReaderInterceptor.class);

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {

        logger.info(this + " executed in order to add content to request");

        return context.proceed();
    }
}
