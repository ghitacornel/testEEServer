package dao.entities;

import dao.exceptions.DAOException;

import javax.ejb.EJB;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * The listener must be public
 */
public class AuditableListener {

    @EJB
    AuditableSupport auditableSupport;

    private String getCurrentUserName() {
        if (auditableSupport == null) {
            throw new DAOException("Auditable support not found");
        }
        return auditableSupport.getCurrentUserName();
    }

    @PrePersist
    public void prePersist(AbstractAuditableEntity entity) {
        entity.creationDate = new Date();
        entity.creator = getCurrentUserName();
        entity.lastUpdateDate = null;
        entity.updater = null;
    }

    @PreUpdate
    public void preUpdate(AbstractAuditableEntity entity) {
        if (entity.getCreator() == null) {
            throw new DAOException("Cannot have null creator for " + entity.toString());
        }
        if (entity.getCreationDate() == null) {
            throw new DAOException("Cannot have null creation date for " + entity.toString());
        }
        entity.lastUpdateDate = new Date();
        entity.updater = getCurrentUserName();
    }

    /**
     * Usually auditable entities must not be removed but marked as removed
     * using a flag
     *
     * @param entity
     */
    @PreRemove
    public void preRemove(AbstractAuditableEntity entity) {
        throw new DAOException("Cannot delete auditable entity " + entity);
    }
}
