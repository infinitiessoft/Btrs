package service;

import java.util.Collection;

import sendto.ExpenseCateTypeSendto;

public interface ExpenseCateTypeService {

	public ExpenseCateTypeSendto retrieve(long id);

	public void delete(long id);

	public ExpenseCateTypeSendto save(ExpenseCateTypeSendto expenseCateType);

	public ExpenseCateTypeSendto update(long id);

	public void revokeExpenseCategoryFromExpenseType(long category_id, long type_id);

	public ExpenseCateTypeSendto findByExpenseCategoryIdAndExpenseTypeId(long category_id, long type_id);

	public void grantExpenseCategoryToExpenseType(long category_id, long type_id);

	public Collection<ExpenseCateTypeSendto> findAll();

}
