package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import entity.ExpenseCategory;
import sendto.ExpenseCategorySendto;

public interface ExpenseCategoryService {

	public ExpenseCategorySendto retrieve(long id);

	public void delete(long id);

	public ExpenseCategorySendto save(ExpenseCategorySendto expenseCategory);

	public Page<ExpenseCategorySendto> findAll(Specification<ExpenseCategory> spec, Pageable pageable);

	public ExpenseCategorySendto update(long id, ExpenseCategorySendto expenseCategory);

}
