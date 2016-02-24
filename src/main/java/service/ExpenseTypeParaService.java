package service;

import java.util.Collection;

import sendto.ExpenseTypeParaSendto;

public interface ExpenseTypeParaService {

	public ExpenseTypeParaSendto retrieve(long id);

	public void delete(long id);

	public ExpenseTypeParaSendto save(ExpenseTypeParaSendto expenseTypePara);

	public ExpenseTypeParaSendto update(long id, ExpenseTypeParaSendto expenseTypePara);

	public void revokeTypeFromParameter(long typeId, long parameterId);

	public Collection<ExpenseTypeParaSendto> findAll();

	public ExpenseTypeParaSendto findByTypeIdAndParameterId(long typeId, long parameterId);

	public void grantTypeToParameter(long typeId, long parameterId);

}
