package dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Extend this interface only when particular DAO methods have to be designed
 * and implemented for a certain Entity<br>
 * Some generic methods receive Object type and not T type as parameter since
 * sometimes one particular DAO might provide operations for a whole strong
 * related set of Entities
 */
public interface GenericDao<Entity, ID extends Serializable> {

	void persist(Entity entity);

	void merge(Entity entity);

	void remove(Entity entity);

	/**
	 * sometimes we want to perform operations on entities that must not affect
	 * their database state<br>
	 * those entities must be detached first
	 *
	 * @param entity
	 */
	void detach(Entity entity);

	/**
	 * sometimes we want to cancel all changes made on an entity
	 *
	 * @param entity
	 */
	void refresh(Entity entity);

	/**
	 * sometimes we might use stored procedures that create/alter existing
	 * entities
	 */
	void flush();

	List<Entity> findAll();

	Entity findById(ID id);

	List<Entity> findByIds(Collection<ID> ids);

	Entity getById(ID id);

	void removeById(ID id);

}
