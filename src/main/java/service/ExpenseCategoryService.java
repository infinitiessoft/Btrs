package service;

import java.util.Collection;

import entity.ExpenseCategory;

public interface ExpenseCategoryService {

	public ExpenseCategory retrieve(long id);

	public void delete(long id);

	public void save(ExpenseCategory expenseCategory);

	public Collection<ExpenseCategory> findAll();

	public void update(long id, ExpenseCategory expenseCategory);
}
