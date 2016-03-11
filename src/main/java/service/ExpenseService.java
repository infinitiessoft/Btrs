package service;

import java.util.Collection;

import sendto.ExpenseSendto;

public interface ExpenseService {

	public ExpenseSendto retrieve(long id);

	public void delete(long id);

	public ExpenseSendto save(ExpenseSendto expense);

	public Collection<ExpenseSendto> findAll();

	public ExpenseSendto update(long id);

}
