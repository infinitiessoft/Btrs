package mail;

import entity.Report;

public interface Mail {
	public abstract String buildSubject(Report report);

	public abstract String buildBody(Report report);

}
