package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import resources.specification.ExpenseCateTypeSpecification;
import sendto.ExpenseTypeSendto;

public interface ExpenseCateTypeService {

	public void revokeExpenseTypeFromExpenseCategory(long expenseCategoryId, long expenseTypeId);

	public Page<ExpenseTypeSendto> findAll(ExpenseCateTypeSpecification spec, Pageable pageable);

	public ExpenseTypeSendto findByExpenseCategoryIdAndExpenseTypeId(long expenseCategoryId, long expenseTypeId);

	public void grantExpenseTypeToExpenseCategory(long expenseCategoryId, long expenseTypeId);
}
