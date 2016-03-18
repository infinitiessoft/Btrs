package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import resources.specification.ExpenseSpecification;
import sendto.ExpenseSendto;

public interface ExpenseService {

	public ExpenseSendto retrieve(long id);

	public void delete(long id);

	public ExpenseSendto save(ExpenseSendto expense);

	public Page<ExpenseSendto> findAll(ExpenseSpecification spec, Pageable pageable);

	public ExpenseSendto update(long id, ExpenseSendto expense);

}
