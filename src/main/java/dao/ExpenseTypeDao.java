package dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import entity.ExpenseType;
import sendto.ExpenseTypeSendto;

public interface ExpenseTypeDao extends JpaSpecificationExecutor<ExpenseType> {

	ExpenseType findOne(long typeId);

	void delete(long id);

	ExpenseType save(ExpenseType type);

	Collection<ExpenseType> findAll();

	ExpenseType save(ExpenseTypeSendto expenseType);

}
