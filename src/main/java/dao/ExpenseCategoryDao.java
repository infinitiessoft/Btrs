package dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import entity.ExpenseCategory;

public interface ExpenseCategoryDao
		extends PagingAndSortingRepository<ExpenseCategory, Long>, JpaSpecificationExecutor<ExpenseCategory> {

}
