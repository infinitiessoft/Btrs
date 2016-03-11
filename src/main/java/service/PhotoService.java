package service;

import java.util.Collection;

import sendto.PhotoSendto;

public interface PhotoService {

	public PhotoSendto retrieve(long id);

	public void delete(long id);

	public PhotoSendto save(PhotoSendto photo);

	public Collection<PhotoSendto> findAll();

	public PhotoSendto update(long id);

}
