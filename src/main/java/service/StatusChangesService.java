package service;

import java.util.Collection;

import entity.StatusChanges;

public interface StatusChangesService {

	public StatusChanges retrieve(long id);

	public void delete(long id);

	public void save(StatusChanges statusChanges);

	public Collection<StatusChanges> findAll();

	public void update(long id, StatusChanges statusChanges);
}
