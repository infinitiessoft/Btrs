package service;

import java.util.Collection;

import sendto.ExpenseCategorySendto;

public interface ExpenseCategoryService {

	public ExpenseCategorySendto retrieve(long id);

	public void delete(long id);

	public ExpenseCategorySendto save(ExpenseCategorySendto expenseCategory);

	public ExpenseCategorySendto update(long id);

	public Collection<ExpenseCategorySendto> findAll();

}
