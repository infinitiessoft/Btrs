package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import resources.specification.PhotoSpecification;
import sendto.PhotoSendto;

public interface PhotoService {

	public PhotoSendto retrieve(long id);

	public void delete(long id);

	public PhotoSendto save(PhotoSendto photo);

	public Page<PhotoSendto> findAll(PhotoSpecification spec, Pageable pageable);

	public PhotoSendto update(long id, PhotoSendto photo);

}
