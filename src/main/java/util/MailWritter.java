package util;

import entity.Report;

public interface MailWritter {

	public abstract String buildSubmittedSubject(Report report);
	public abstract String buildReviewedSubject(Report report);
	public abstract String buildSubmittedBody(Report report);
	public abstract String buildReviewedBody(Report report);

}