package dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import entity.Photo;
import sendto.PhotoSendto;

public interface PhotoDao extends JpaSpecificationExecutor<Photo> {

	Photo findOne(long id);

	void delete(long id);

	Photo save(Photo pht);

	Collection<Photo> findAll();

	Photo save(PhotoSendto photo);

}
