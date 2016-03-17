package sendto;

import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

public class PhotoSendto {

	private Long id;
	private String fileName;
	private String contentType;
	private Date uploadDate;
	private String title;
	private byte[] data;
	private int size;
	private Report report;

	private boolean isFileNameSet;
	private boolean isContentTypeSet;
	private boolean isUploadDateSet;
	private boolean isTitleSet;
	private boolean isDataSet;
	private boolean isSizeSet;
	private boolean isReportSet;

	public PhotoSendto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		isFileNameSet = true;
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		if (report != null) {
			isReportSet = true;
		}
		this.report = report;
	}

	@XmlTransient
	public boolean isFileNameSet() {
		return isFileNameSet;
	}

	@XmlTransient
	public boolean isContentTypeSet() {
		return isContentTypeSet;
	}

	@XmlTransient
	public boolean isUploadDateSet() {
		return isUploadDateSet;
	}

	@XmlTransient
	public boolean isTitleSet() {
		return isTitleSet;

	}

	@XmlTransient
	public boolean isDataSet() {
		return isDataSet;
	}

	@XmlTransient
	public boolean isSizeSet() {
		return isSizeSet;
	}

	@XmlTransient
	public boolean isReportSet() {
		return isReportSet;
	}

	public static class Report {
		private Long id;

		private boolean isIdSet;

		public Report() {
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			setIdSet(true);
			this.id = id;
		}

		@XmlTransient
		public boolean isIdSet() {
			return isIdSet;
		}

		@XmlTransient
		public void setIdSet(boolean isIdSet) {
			this.isIdSet = isIdSet;
		}

	}

}
