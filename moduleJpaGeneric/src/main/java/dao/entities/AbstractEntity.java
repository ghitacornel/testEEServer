package dao.entities;

import java.io.Serializable;

/**
 * a generic entity
 *
 * @author Cornel
 */
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {

    public abstract ID getId();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getId() == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AbstractEntity<?> other = (AbstractEntity<?>) obj;
        ID id = getId();
        if (id == null) {
            return false;
        }
        return id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        ID id = getId();
        if (id == null) {
            return 1;
        }
        return id.hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getCanonicalName() + " [id=" + getId() + "]";
    }

}
