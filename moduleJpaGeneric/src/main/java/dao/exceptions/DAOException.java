package dao.exceptions;

/**
 * DAO exceptions must be unchecked exceptions.<br>
 * DAO exceptions must be used for wrapping all internally raised DAO
 * exceptions.<br>
 * DAO exceptions should not be processed.<br>
 * DAO exceptions recovery is usually not possible (recovery from a database
 * exception is usually not possible).
 */
@SuppressWarnings("serial")
public class DAOException extends RuntimeException {

    public DAOException(Exception e) {
        super(e);
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Exception e) {
        super(message, e);
    }
}
