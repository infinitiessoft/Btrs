package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import sendto.JobTitleSendto;
import service.JobTitleService;
import dao.JobTitleDao;
import entity.JobTitle;
import exceptions.JobTitleNotFoundException;

public class JobTitleServiceImpl implements JobTitleService {

	private JobTitleDao jobTitleDao;

	public JobTitleServiceImpl(JobTitleDao jobTitleDao) {
		this.jobTitleDao = jobTitleDao;
	}

	@Transactional("transactionManager")
	@Override
	public JobTitleSendto retrieve(long id) {
		JobTitle jobTitle = jobTitleDao.findOne(id);
		if (jobTitle == null) {
			throw new JobTitleNotFoundException(id);
		}
		return toJobTitleSendto(jobTitle);
	}

	@Transactional("transactionManager")
	@Override
	public void delete(long id) {
		try {
			JobTitle jobTitle = jobTitleDao.findOne(id);
			if (jobTitle == null) {
				throw new JobTitleNotFoundException(id);
			}
			jobTitleDao.delete(jobTitle);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			throw new JobTitleNotFoundException(id);
		}

	}

	@Transactional("transactionManager")
	@Override
	public JobTitleSendto findByName(String name) {
		return toJobTitleSendto(jobTitleDao.findByName(name));
	}

	@Transactional("transactionManager")
	@Override
	public JobTitleSendto save(JobTitleSendto jobTitle) {
		jobTitle.setId(null);
		JobTitle newEntry = new JobTitle();
		setUpJobTitle(jobTitle, newEntry);
		return toJobTitleSendto(jobTitleDao.save(newEntry));
	}

	@Transactional("transactionManager")
	@Override
	public Page<JobTitleSendto> findAll(Specification<JobTitle> spec,
			Pageable pageable) {
		List<JobTitleSendto> sendtos = new ArrayList<JobTitleSendto>();
		Page<JobTitle> jobTitles = jobTitleDao.findAll(spec, pageable);
		for (JobTitle jobTitle : jobTitles) {
			sendtos.add(toJobTitleSendto(jobTitle));
		}
		Page<JobTitleSendto> rets = new PageImpl<JobTitleSendto>(sendtos,
				pageable, jobTitles.getTotalElements());
		return rets;
	}

	@Transactional("transactionManager")
	@Override
	public JobTitleSendto update(long id, JobTitleSendto updated) {
		JobTitle dept = jobTitleDao.findOne(id);
		if (dept == null) {
			throw new JobTitleNotFoundException(id);
		}
		setUpJobTitle(updated, dept);
		return toJobTitleSendto(jobTitleDao.save(dept));
	}

	private void setUpJobTitle(JobTitleSendto sendto, JobTitle newEntry) {
		if (sendto.isNameSet()) {
			newEntry.setName(sendto.getName());
		}

		if (sendto.isCommentSet()) {
			newEntry.setComment(sendto.getComment());
		}

	}

	private JobTitleSendto toJobTitleSendto(JobTitle jobTitle) {
		JobTitleSendto ret = new JobTitleSendto();
		ret.setId(jobTitle.getId());
		ret.setName(jobTitle.getName());
		ret.setComment(jobTitle.getComment());
		return ret;
	}

}
