package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import resources.specification.ExpenseTypeParaSpecification;
import sendto.ParameterValueSendto;

public interface ExpenseTypeParaService {

	public void revokeParameterValueFromExpenseType(long parameterId, long expenseTypeId);

	public ParameterValueSendto findByExpenseTypeIdAndParameterValueId(long expenseTypeId, long parameterId);

	public void grantParameterValueToExpenseType(long expenseTypeId, long parameterId);

	public Page<ParameterValueSendto> findAll(ExpenseTypeParaSpecification spec, Pageable pageable);
}
