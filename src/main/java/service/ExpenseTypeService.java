package service;

import java.util.Collection;

import sendto.ExpenseTypeSendto;

public interface ExpenseTypeService {

	public ExpenseTypeSendto retrieve(long id);

	public void delete(long id);

	public ExpenseTypeSendto save(ExpenseTypeSendto expenseType);

	public Collection<ExpenseTypeSendto> findAll();

	public ExpenseTypeSendto update(long id);

}
