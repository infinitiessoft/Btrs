package dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import entity.Photo;

public interface PhotoDao extends PagingAndSortingRepository<Photo, Long>, JpaSpecificationExecutor<Photo> {

}
