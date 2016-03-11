package service;

import java.util.Collection;

import sendto.ReportSendto;

public interface ReportService {

	public ReportSendto retrieve(long id);

	public void delete(long id);

	public ReportSendto save(ReportSendto report);

	public Collection<ReportSendto> findAll();

	public ReportSendto update(long id);

}
