package filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;

@PreMatching
public class PreMatchRequestFilter implements ContainerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(PreMatchRequestFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) {
        logger.info(this + " executed before the request is matched");
    }

}
