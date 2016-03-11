package dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import entity.ExpenseType;

public interface ExpenseTypeDao
		extends PagingAndSortingRepository<ExpenseType, Long>, JpaSpecificationExecutor<ExpenseType> {

}
