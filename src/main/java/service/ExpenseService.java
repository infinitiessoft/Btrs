package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import sendto.ExpenseSendto;
import entity.Expense;

public interface ExpenseService {

	public ExpenseSendto retrieve(Specification<Expense> spec);

	public void delete(Specification<Expense> spec);

	public ExpenseSendto save(ExpenseSendto expense);

	public Page<ExpenseSendto> findAll(Specification<Expense> spec,
			Pageable pageable);

	public ExpenseSendto update(Specification<Expense> spec,
			ExpenseSendto expense);

}
