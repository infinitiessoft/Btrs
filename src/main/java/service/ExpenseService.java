package service;

import java.util.Collection;

import entity.Expense;

public interface ExpenseService {

	public Expense retrieve(long id);

	public void delete(long id);

	public void save(Expense expense);

	public Collection<Expense> findAll();

	public void update(long id, Expense expense);

}
