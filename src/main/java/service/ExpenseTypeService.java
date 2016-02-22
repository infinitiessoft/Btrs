package service;

import java.util.Collection;

import entity.ExpenseType;

public interface ExpenseTypeService {
	public ExpenseType retrieve(long id);

	public void delete(long id);

	public void save(ExpenseType expenseType);

	public Collection<ExpenseType> findAll();

	public void update(long id, ExpenseType expenseType);

}
