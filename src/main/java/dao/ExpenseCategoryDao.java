package dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import entity.ExpenseCategory;
import sendto.ExpenseCategorySendto;

public interface ExpenseCategoryDao extends JpaSpecificationExecutor<ExpenseCategory> {

	ExpenseCategory findOne(long id);

	void delete(long id);

	ExpenseCategory save(ExpenseCategory expCat);

	Collection<ExpenseCategory> findAll();

	ExpenseCategory save(ExpenseCategorySendto expenseCategory);

}
