package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import entity.Report;
import sendto.ReportSendto;

public interface ReportService {

	public ReportSendto retrieve(long id);

	public void delete(long id);

	public ReportSendto save(ReportSendto report);

	public Page<ReportSendto> findAll(Specification<Report> spec, Pageable pageable);

	public ReportSendto update(long id, ReportSendto report);

	public ReportSendto findByOwnerId(long id);

}
