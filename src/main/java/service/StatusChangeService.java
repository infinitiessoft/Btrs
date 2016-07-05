package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import sendto.StatusChangeSendto;
import entity.StatusChange;

public interface StatusChangeService {

	public StatusChangeSendto retrieve(Specification<StatusChange> spec);

	// public void delete(StatusChangeSpecification spec);

	// public StatusChangeSendto save(StatusChangeSendto statusChanges);

	public Page<StatusChangeSendto> findAll(Specification<StatusChange> spec,
			Pageable pageable);

	// public StatusChangeSendto update(StatusChangeSpecification spec,
	// StatusChangeSendto statusChanges);

}
