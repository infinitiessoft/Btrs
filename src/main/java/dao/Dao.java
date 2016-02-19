package dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<T extends Serializable, I> {

	static final long serialVersionUID = 7526471155622776147L;

	List<T> findAll();

	T find(I id);

	T save(T newStatus);

	void delete(I id);

}
