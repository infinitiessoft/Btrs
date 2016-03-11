package service;

import java.util.Collection;

import sendto.ExpenseTypeParaSendto;

public interface ExpenseTypeParaService {

	public ExpenseTypeParaSendto retrieve(long id);

	public void delete(long id);

	public ExpenseTypeParaSendto save(ExpenseTypeParaSendto expenseTypePara);

	public ExpenseTypeParaSendto update(long id);

	public Collection<ExpenseTypeParaSendto> findAll();

	public ExpenseTypeParaSendto findByExpenseTypeIdAndParameterValueId(long expense_id, long parameterValue_id);

	public void grantExpenseTypeToParameterValue(long expense_id, long parameterValue_id);

	public void revokeExpenseTypeFromParameterValue(long id, long expense_id);

}
