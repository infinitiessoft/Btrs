package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import resources.specification.StatusChangesSpecification;
import sendto.StatusChangesSendto;

public interface StatusChangesService {

	public StatusChangesSendto retrieve(long id);

	public void delete(long id);

	public StatusChangesSendto save(StatusChangesSendto statusChanges);

	public Page<StatusChangesSendto> findAll(StatusChangesSpecification spec, Pageable pageable);

	public StatusChangesSendto update(long id, StatusChangesSendto statusChanges);

}
