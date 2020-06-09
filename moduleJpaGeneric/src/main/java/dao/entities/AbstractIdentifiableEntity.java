package dao.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * a generic entity with generic id policy
 *
 * @author Cornel
 */
@MappedSuperclass
public abstract class AbstractIdentifiableEntity extends AbstractEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Override
    public Integer getId() {
        return id;
    }

}
