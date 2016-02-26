package dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import entity.Report;
import sendto.ReportSendto;

public interface ReportDao extends JpaSpecificationExecutor<Report> {

	Report findOne(long id);

	void delete(long id);

	Report save(Report rpt);

	Report save(ReportSendto report);

	Collection<Report> findAll();

}
