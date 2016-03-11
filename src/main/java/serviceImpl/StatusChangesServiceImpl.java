package serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dao.StatusChangesDao;
import entity.StatusChanges;
import enumpackage.StatusEnum;
import exceptions.StatusChangesNotFoundException;
import sendto.StatusChangesSendto;
import service.StatusChangesService;

public class StatusChangesServiceImpl implements StatusChangesService {

	private StatusChangesDao statusChangesDao;

	public StatusChangesServiceImpl(StatusChangesDao statusDao) {
		this.statusChangesDao = statusDao;
	}

	@Override
	public StatusChangesSendto retrieve(long id) {
		StatusChanges status = statusChangesDao.findOne(id);
		if (status == null) {
			throw new StatusChangesNotFoundException(id);
		}
		return toStatusChangesSendto(status);
	}

	private StatusChangesSendto toStatusChangesSendto(StatusChanges status) {
		StatusChangesSendto ret = new StatusChangesSendto();
		ret.setId(status.getId());
		ret.setCreatedDate(status.getCreatedDate());
		ret.setComment(status.getComment());
		return ret;
	}

	@Override
	public void delete(long id) {
		statusChangesDao.delete(id);

	}

	@Override
	public StatusChangesSendto save(StatusChangesSendto statusChanges) {
		statusChanges.setId(null);
		StatusChanges status = new StatusChanges();
		status = statusChangesDao.save(status);
		return toStatusChangesSendto(status);
	}

	@Override
	public Collection<StatusChangesSendto> findAll() {
		List<StatusChangesSendto> sendto = new ArrayList<StatusChangesSendto>();
		for (StatusChanges statusChanges : statusChangesDao.findAll()) {
			sendto.add(toStatusChangesSendto(statusChanges));
		}
		return sendto;
	}

	@Override
	public StatusChangesSendto update(long id) {
		StatusChanges status = statusChangesDao.findOne(id);
		if (status == null) {
			throw new StatusChangesNotFoundException(id);
		}
		return toStatusChangesSendto(statusChangesDao.save(status));
	}

	@Override
	public StatusChangesSendto reject(long id) {
		StatusChanges status = statusChangesDao.findOne(id);
		if (status == null) {
			throw new StatusChangesNotFoundException(id);
		}
		status.setStatus(StatusEnum.reject);
		status = statusChangesDao.save(status);
		return toStatusChangesSendto(status);
	}

	@Override
	public StatusChangesSendto permit(long id) {
		StatusChanges status = statusChangesDao.findOne(id);
		if (status == null) {
			throw new StatusChangesNotFoundException(id);
		}
		status.setStatus(StatusEnum.approved);
		status = statusChangesDao.save(status);
		return toStatusChangesSendto(status);
	}

}
