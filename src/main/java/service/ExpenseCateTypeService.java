package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import resources.specification.ExpenseCateTypeSpecification;
import sendto.ExpenseTypeSendto;

public interface ExpenseCateTypeService {

	public void revokeExpenseTypeFromExpenseCategory(long expenseTypeId, long expenseCategoryId);

	public Page<ExpenseTypeSendto> findAll(ExpenseCateTypeSpecification spec, Pageable pageable);

	public ExpenseTypeSendto findByExpenseCategoryIdAndExpenseTypeId(long expenseTypeId, long expenseCategoryId);

	public void grantExpenseTypeToExpenseCategory(long expenseTypeId, long expenseCategoryId);
}
