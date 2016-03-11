package service;

import java.util.Collection;

import sendto.StatusChangesSendto;

public interface StatusChangesService {

	public StatusChangesSendto retrieve(long id);

	public void delete(long id);

	public StatusChangesSendto save(StatusChangesSendto statusChanges);

	public Collection<StatusChangesSendto> findAll();

	public StatusChangesSendto update(long id);

	public StatusChangesSendto reject(long id);

	public StatusChangesSendto permit(long id);
}
