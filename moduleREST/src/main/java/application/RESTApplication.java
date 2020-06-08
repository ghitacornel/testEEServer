package application;

import controllers.PersonController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest")
public class RESTApplication extends Application {

    private final Set<Class<?>> classes = new HashSet<>();

    public RESTApplication() {
        classes.add(PersonController.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}
