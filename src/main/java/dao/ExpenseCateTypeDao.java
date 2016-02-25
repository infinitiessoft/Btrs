package dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import entity.ExpenseCateType;
import sendto.ExpenseCateTypeSendto;

public interface ExpenseCateTypeDao extends JpaSpecificationExecutor<ExpenseCateType> {

	ExpenseCateType findOne(long id);

	void delete(long id);

	ExpenseCateType save(ExpenseCateType cateType);

	ExpenseCateType save(ExpenseCateTypeSendto expenseCateType);

	ExpenseCateType findByEmployeeIdAndRoleId(long categoryId, long typeId);

	void delete(ExpenseCateType expenseCateType);

	Collection<ExpenseCateType> findAll();

}
