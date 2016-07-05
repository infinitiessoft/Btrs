package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import resources.specification.ReportSpecification;
import sendto.ReportSendto;
import sendto.ReportSummarySendto;
import sendto.StatusChangeSendto;

public interface ReportService {

	public ReportSendto retrieve(ReportSpecification spec);

	public void delete(ReportSpecification spec);

	public ReportSendto save(ReportSendto report);

	public Page<ReportSummarySendto> findAll(ReportSpecification spec,
			Pageable pageable);

	public ReportSendto update(ReportSpecification spec, ReportSendto report,
			long currentUseId);

	public ReportSendto approve(long id, StatusChangeSendto statusChange,
			long currentUserId);

	public ReportSendto reject(long id, StatusChangeSendto statusChange,
			long currentUserId);

}
