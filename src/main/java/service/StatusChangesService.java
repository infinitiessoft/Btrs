package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import resources.specification.StatusChangesSpecification;
import sendto.StatusChangeSendto;

public interface StatusChangesService {

	public StatusChangeSendto retrieve(long id);

	public void delete(long id);

	public StatusChangeSendto save(StatusChangeSendto statusChanges);

	public Page<StatusChangeSendto> findAll(StatusChangesSpecification spec, Pageable pageable);

	public StatusChangeSendto update(long id, StatusChangeSendto statusChanges);

}
