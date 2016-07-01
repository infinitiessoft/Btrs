package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import resources.specification.StatusChangeSpecification;
import sendto.StatusChangeSendto;

public interface StatusChangeService {

	public StatusChangeSendto retrieve(StatusChangeSpecification spec);

	// public void delete(StatusChangeSpecification spec);

	// public StatusChangeSendto save(StatusChangeSendto statusChanges);

	public Page<StatusChangeSendto> findAll(StatusChangeSpecification spec,
			Pageable pageable);

	// public StatusChangeSendto update(StatusChangeSpecification spec,
	// StatusChangeSendto statusChanges);

}
