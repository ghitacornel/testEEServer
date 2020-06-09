package dao.exceptions;

public class DAOEntityNotFoundException extends DAOException {

    public DAOEntityNotFoundException(String entity) {
        super(entity + " with null id provided");
    }

    public DAOEntityNotFoundException(String entity, Object id) {
        super("No instance of type " + entity + " found for id " + id);
    }
}
