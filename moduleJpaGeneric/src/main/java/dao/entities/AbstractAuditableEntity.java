package dao.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * all auditable entitites must extend this abstract class<br>
 * all auditable entities must be persisted in tables that contain these
 * mandatory columns<br>
 * field setters as seen here can be made default accessible
 */
@MappedSuperclass
@EntityListeners(AuditableListener.class)
public abstract class AbstractAuditableEntity extends AbstractIdentifiableEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creationDate", nullable = false)
    Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdatedDate")
    Date lastUpdateDate;

    @Basic(optional = false)
    @Column(name = "creator", nullable = false)
    String creator;

    @Basic(optional = true)
    @Column(name = "updater")
    String updater;

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public String getCreator() {
        return creator;
    }

    public String getUpdater() {
        return updater;
    }

    @Override
    public String toString() {
        return this.getClass().getCanonicalName() + " [id=" + getId() + ", creationDate=" + creationDate
                + ", lastUpdateDate=" + lastUpdateDate + ", creator=" + creator + ", updater=" + updater + "]";
    }

}
