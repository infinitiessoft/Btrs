package dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import entity.ExpenseTypePara;

public interface ExpenseTypeParaDao
		extends PagingAndSortingRepository<ExpenseTypePara, Long>, JpaSpecificationExecutor<ExpenseTypePara> {

	ExpenseTypePara findByExpenseTypeIdAndTypeParameterId(long expenseTypeId, long typeParameterId);

}
