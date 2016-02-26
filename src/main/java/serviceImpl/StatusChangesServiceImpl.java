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

	private StatusChangesDao statusDao;

	public StatusChangesServiceImpl(StatusChangesDao statusDao) {
		this.statusDao = statusDao;
	}

	@Override
	public StatusChangesSendto retrieve(long id) {
		StatusChanges status = statusDao.findOne(id);
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
		statusDao.delete(id);

	}

	@Override
	public StatusChangesSendto save(StatusChangesSendto statusChanges) {
		statusChanges.setId(null);
		StatusChanges status = new StatusChanges();
		status = statusDao.save(status);
		return toStatusChangesSendto(status);
	}

	@Override
	public Collection<StatusChangesSendto> findAll() {
		List<StatusChangesSendto> sendto = new ArrayList<StatusChangesSendto>();
		Collection<StatusChanges> status = statusDao.findAll();
		for (StatusChanges statusChanges : status) {
			sendto.add(toStatusChangesSendto(statusChanges));
		}
		return sendto;
	}

	@Override
	public StatusChangesSendto update(long id, StatusChangesSendto statusChanges) {
		StatusChanges status = statusDao.findOne(id);
		if (status == null) {
			throw new StatusChangesNotFoundException(id);
		}
		return toStatusChangesSendto(statusDao.save(statusChanges));
	}

	@Override
	public StatusChangesSendto reject(long id) {
		StatusChanges status = statusDao.findOne(id);
		if (status == null) {
			throw new StatusChangesNotFoundException(id);
		}
		status.setStatus(StatusEnum.reject);
		status = statusDao.save(status);
		return toStatusChangesSendto(status);
	}

	@Override
	public StatusChangesSendto permit(long id) {
		StatusChanges status = statusDao.findOne(id);
		if (status == null) {
			throw new StatusChangesNotFoundException(id);
		}
		status.setStatus(StatusEnum.approved);
		status = statusDao.save(status);
		return toStatusChangesSendto(status);
	}

}
