package filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

public class AfterMatchRequestFilter implements ContainerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(AfterMatchRequestFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) {
        logger.info(this + " executed after the request is matched");
    }

}
