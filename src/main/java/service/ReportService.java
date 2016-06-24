package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import sendto.ReportSendto;
import sendto.ReportSummarySendto;
import sendto.StatusChangeSendto;
import entity.Report;

public interface ReportService {

	public ReportSendto retrieve(long id);

	public ReportSendto retrieve(Specification<Report> spec);

	public void delete(long id);

	public void delete(Specification<Report> spec);

	public ReportSendto save(ReportSendto report);

	public Page<ReportSummarySendto> findAll(Specification<Report> spec,
			Pageable pageable);

	public ReportSendto update(long id, ReportSendto report,
			String currentUseName);

	public ReportSendto update(Specification<Report> spec, ReportSendto report,
			long currentId);

	public ReportSendto approve(long id, StatusChangeSendto statusChange,
			long currentUserId);

	public ReportSendto reject(long id, StatusChangeSendto statusChange,
			long currentUserId);

}
