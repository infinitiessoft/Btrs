package dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import entity.Report;

public interface ReportDao extends PagingAndSortingRepository<Report, Long>, JpaSpecificationExecutor<Report> {

	Report findByOwnerId(long owner_id);

}
