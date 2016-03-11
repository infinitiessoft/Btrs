package dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import entity.TypeParameter;

public interface TypeParameterDao
		extends PagingAndSortingRepository<TypeParameter, Long>, JpaSpecificationExecutor<TypeParameter> {

}
