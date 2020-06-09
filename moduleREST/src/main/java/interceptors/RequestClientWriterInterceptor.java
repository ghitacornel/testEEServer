package interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.IOException;

public class RequestClientWriterInterceptor implements WriterInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestClientWriterInterceptor.class);

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {

        // executed only if content exists
        logger.info(this + " executed in order to add content to response");

        context.proceed();
    }
}
