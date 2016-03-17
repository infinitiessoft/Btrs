package serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import dao.PhotoDao;
import dao.ReportDao;
import entity.Photo;
import exceptions.PhotoNotFoundException;
import exceptions.ReportNotFoundException;
import resources.specification.PhotoSpecification;
import sendto.PhotoSendto;
import service.PhotoService;

public class PhotoServiceImpl implements PhotoService {

	private PhotoDao photoDao;
	private ReportDao reportDao;

	public PhotoServiceImpl(PhotoDao photoDao, ReportDao reportDao) {
		this.photoDao = photoDao;
		this.reportDao = reportDao;
	}

	@Transactional
	@Override
	public PhotoSendto retrieve(long id) {
		Photo photo = photoDao.findOne(id);
		if (photo == null) {
			throw new PhotoNotFoundException(id);
		}
		return toPhotoSendto(photo);
	}

	private PhotoSendto toPhotoSendto(Photo photo) {
		PhotoSendto ret = new PhotoSendto();
		ret.setId(photo.getId());
		ret.setFileName(photo.getFileName());
		ret.setUploadDate(photo.getUploadDate());
		ret.setTitle(photo.getTitle());
		ret.setData(photo.getData());
		ret.setSize(photo.getSize());
		ret.setContentType(photo.getContentType());
		PhotoSendto.Report rpt = new PhotoSendto.Report();
		rpt.setId(photo.getReport().getId());
		ret.setReport(rpt);
		return ret;
	}

	@Transactional
	@Override
	public void delete(long id) {
		try {
			Photo photo = photoDao.findOne(id);
			if (photo == null) {
				throw new PhotoNotFoundException(id);
			}
			photoDao.delete(photo);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			throw new PhotoNotFoundException(id);
		}
	}

	@Transactional
	@Override
	public PhotoSendto save(PhotoSendto photo) {
		photo.setId(null);
		Photo newEntry = new Photo();
		setUpPhoto(photo, newEntry);
		return toPhotoSendto(photoDao.save(newEntry));
	}

	@Transactional
	@Override
	public Page<PhotoSendto> findAll(PhotoSpecification spec, Pageable pageable) {
		List<PhotoSendto> sendto = new ArrayList<PhotoSendto>();
		Page<Photo> photos = photoDao.findAll(spec, pageable);
		for (Photo photo : photos) {
			sendto.add(toPhotoSendto(photo));
		}
		Page<PhotoSendto> rets = new PageImpl<PhotoSendto>(sendto, pageable, photos.getTotalElements());
		return rets;
	}

	@Transactional
	@Override
	public PhotoSendto update(long id, PhotoSendto updated) {
		Photo pht = photoDao.findOne(id);
		if (pht == null) {
			throw new PhotoNotFoundException(id);
		}
		setUpPhoto(updated, pht);
		return toPhotoSendto(photoDao.save(pht));
	}

	private void setUpPhoto(PhotoSendto sendto, Photo newEntry) {
		if (sendto.isFileNameSet()) {
			newEntry.setFileName(sendto.getFileName());
		}
		if (sendto.isContentTypeSet()) {
			newEntry.setContentType(sendto.getContentType());
		}
		if (sendto.isUploadDateSet()) {
			newEntry.setUploadDate(sendto.getUploadDate());
		}
		if (sendto.isTitleSet()) {
			newEntry.setTitle(sendto.getTitle());
		}
		if (sendto.isDataSet()) {
			newEntry.setData(sendto.getData());
		}
		if (sendto.isSizeSet()) {
			newEntry.setSize(sendto.getSize());
		}
		if (sendto.isReportSet()) {
			if (sendto.getReport().isIdSet()) {
				entity.Report report = reportDao.findOne(sendto.getReport().getId());
				if (report == null) {
					throw new ReportNotFoundException(sendto.getReport().getId());
				}
				newEntry.setReport(report);
			}
		}

	}

}
