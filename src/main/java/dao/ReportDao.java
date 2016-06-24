package dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import entity.Report;

public interface ReportDao extends PagingAndSortingRepository<Report, Long>, JpaSpecificationExecutor<Report> {

	Report findTopByCreatedDateBeforeOrderByIdDesc(Date createdDate);
}
