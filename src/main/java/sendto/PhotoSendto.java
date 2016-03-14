package sendto;

import java.util.Date;

import entity.Report;

public class PhotoSendto {

	private Long id;
	private String fileName;
	private String contentType;
	private Date uploadDate;
	private String title;
	private byte[] data;
	private int size;
	private Report report;

	public PhotoSendto() {

	}

	public PhotoSendto(Long id, String fileName, String contentType, Date uploadDate, String title, byte[] data,
			int size, Report report) {
		this.id = id;
		this.fileName = fileName;
		this.contentType = contentType;
		this.uploadDate = uploadDate;
		this.title = title;
		this.data = data;
		this.size = size;
		this.report = report;
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
		this.report = report;
	}

}
