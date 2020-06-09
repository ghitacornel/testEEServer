package exceptions;

import dao.exceptions.DAOEntityNotFoundException;
import dao.exceptions.DAOException;

import javax.ws.rs.core.Response;

public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {

        BusinessException businessException = isCausedBy(e, BusinessException.class);
        if (businessException != null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(businessException.getMessage()).build();
        }

        DAOEntityNotFoundException daoEntityNotFoundException = isCausedBy(e, DAOEntityNotFoundException.class);
        if (daoEntityNotFoundException != null) {
            return Response.status(Response.Status.NOT_FOUND).entity(daoEntityNotFoundException.getMessage()).build();
        }

        DAOException daoException = isCausedBy(e, DAOException.class);
        if (daoException != null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(daoException.getMessage()).build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    private static <T> T isCausedBy(Throwable e, Class<T> target) {
        if (e == null) return null;
        if (target.isAssignableFrom(e.getClass())) return (T) e;
        if (e.getCause() == null) return null;
        return isCausedBy(e.getCause(), target);
    }

}
