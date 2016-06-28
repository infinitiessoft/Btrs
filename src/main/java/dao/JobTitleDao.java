package dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import entity.JobTitle;

public interface JobTitleDao extends
		PagingAndSortingRepository<JobTitle, Long>,
		JpaSpecificationExecutor<JobTitle> {

	JobTitle findByName(String name);

}
