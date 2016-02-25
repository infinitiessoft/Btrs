package dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import entity.Expense;
import sendto.ExpenseSendto;

public interface ExpenseDao extends JpaSpecificationExecutor<Expense> {

	Expense findOne(long id);

	void delete(long id);

	Expense save(Expense exp);

	Collection<Expense> findAll();

	Expense save(ExpenseSendto expense);

}
