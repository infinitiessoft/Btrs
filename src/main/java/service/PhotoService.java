package service;

import java.util.Collection;

import entity.Photo;

public interface PhotoService {

	public Photo retrieve(long id);

	public void delete(long id);

	public void save(Photo photo);

	public Collection<Photo> findAll();

	public void update(long id, Photo photo);

}
