package dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import entity.StatusChanges;
import sendto.StatusChangesSendto;

public interface StatusChangesDao extends JpaSpecificationExecutor<StatusChanges> {

	StatusChanges findOne(long id);

	void delete(long id);

	StatusChanges save(StatusChanges status);

	Collection<StatusChanges> findAll();

	StatusChanges save(StatusChangesSendto statusChanges);

}
