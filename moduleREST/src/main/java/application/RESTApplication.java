package application;

import controllers.PersonController;
import filters.AfterMatchRequestFilter;
import filters.PreMatchRequestFilter;
import filters.ResponseServerFilter;
import interceptors.RequestClientWriterInterceptor;
import interceptors.RequestServerReaderInterceptor;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest")
public class RESTApplication extends Application {

    private final Set<Class<?>> classes = new HashSet<>();
    private final Set<Object> singletons = new HashSet<>();

    public RESTApplication() {

        classes.add(PersonController.class);

        singletons.add(new AfterMatchRequestFilter());
        singletons.add(new PreMatchRequestFilter());
        singletons.add(new ResponseServerFilter());
        singletons.add(new RequestClientWriterInterceptor());
        singletons.add(new RequestServerReaderInterceptor());

    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
