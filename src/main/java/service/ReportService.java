package service;

import java.util.Collection;

import entity.Report;

public interface ReportService {

	public Report retrieve(long id);

	public void delete(long id);

	public void save(Report report);

	public Collection<Report> findAll();

	public void update(long id, Report report);
}
