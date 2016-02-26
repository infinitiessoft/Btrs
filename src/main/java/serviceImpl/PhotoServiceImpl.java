package serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dao.PhotoDao;
import entity.Photo;
import exceptions.PhotoNotFoundException;
import sendto.PhotoSendto;
import service.PhotoService;

public class PhotoServiceImpl implements PhotoService {

	private PhotoDao photoDao;

	public PhotoServiceImpl(PhotoDao photoDao) {
		this.photoDao = photoDao;
	}

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
		ret.setReport(photo.getReport());
		ret.setContentType(photo.getContentType());
		return ret;
	}

	@Override
	public void delete(long id) {
		photoDao.delete(id);
	}

	@Override
	public PhotoSendto save(PhotoSendto photo) {
		photo.setId(null);
		Photo pht = new Photo();
		pht = photoDao.save(pht);
		return toPhotoSendto(pht);
	}

	@Override
	public Collection<PhotoSendto> findAll() {
		List<PhotoSendto> sendto = new ArrayList<PhotoSendto>();
		Collection<Photo> photo = photoDao.findAll();
		for (Photo photos : photo) {
			sendto.add(toPhotoSendto(photos));
		}
		return sendto;
	}

	@Override
	public PhotoSendto update(long id, PhotoSendto photo) {
		Photo pht = photoDao.findOne(id);
		if (pht == null) {
			throw new PhotoNotFoundException(id);
		}
		return toPhotoSendto(photoDao.save(photo));
	}

}
