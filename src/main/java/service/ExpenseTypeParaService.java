package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import resources.specification.ExpenseTypeParaSpecification;
import sendto.ExpenseTypeParaSendto;

public interface ExpenseTypeParaService {

	public void revokeTypeParameterFromExpenseType(long typeParameterId, long expenseTypeId);

	public ExpenseTypeParaSendto findByExpenseTypeIdAndParameterValueId(long expenseTypeId, long typeParameterId);

	public void grantTypeParameterToExpenseType(long typeParameterId, long expenseTypeId);

	public Page<ExpenseTypeParaSendto> findAll(ExpenseTypeParaSpecification spec, Pageable pageable);
}
