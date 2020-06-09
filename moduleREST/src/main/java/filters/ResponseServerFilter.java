package filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

public class ResponseServerFilter implements ContainerResponseFilter {

    private static final Logger logger = LoggerFactory.getLogger(ResponseServerFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        logger.info(this + " executed after server response");
    }

}
