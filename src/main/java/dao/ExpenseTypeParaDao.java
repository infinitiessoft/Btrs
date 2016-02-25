package dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import entity.ExpenseTypePara;
import sendto.ExpenseTypeParaSendto;

public interface ExpenseTypeParaDao extends JpaSpecificationExecutor<ExpenseTypePara> {

	ExpenseTypePara findOne(long id);

	void delete(long id);

	ExpenseTypePara save(ExpenseTypePara typePara);

	ExpenseTypePara save(ExpenseTypeParaSendto expenseTypePara);

	ExpenseTypePara findByEmployeeIdAndRoleId(long typeId, long parameterId);

	void delete(ExpenseTypePara expenseTypePara);

	Collection<ExpenseTypePara> findAll();

}
