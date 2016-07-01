package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import sendto.ExpenseCategorySendto;
import entity.ExpenseCategory;

public interface ExpenseCategoryService {

	public ExpenseCategorySendto retrieve(Specification<ExpenseCategory> spec);

	public void delete(Specification<ExpenseCategory> spec);

	public ExpenseCategorySendto save(ExpenseCategorySendto expenseCategory);

	public Page<ExpenseCategorySendto> findAll(
			Specification<ExpenseCategory> spec, Pageable pageable);

	public ExpenseCategorySendto update(Specification<ExpenseCategory> spec,
			ExpenseCategorySendto expenseCategory);

}
