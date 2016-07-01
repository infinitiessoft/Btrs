package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import sendto.JobTitleSendto;
import entity.JobTitle;

public interface JobTitleService {

	public JobTitleSendto retrieve(Specification<JobTitle> spec);

	public void delete(Specification<JobTitle> spec);

	// public JobTitleSendto findByName(String name);

	public JobTitleSendto save(JobTitleSendto jobTitle);

	JobTitleSendto update(Specification<JobTitle> spec, JobTitleSendto jobTitle);

	Page<JobTitleSendto> findAll(Specification<JobTitle> spec, Pageable pageable);

}
