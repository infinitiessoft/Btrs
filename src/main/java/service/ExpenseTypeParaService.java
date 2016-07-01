//package service;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//
//import resources.specification.ExpenseTypeParaSpecification;
//import sendto.TypeParameterSendto;
//
//public interface ExpenseTypeParaService {
//
//	public void revokeTypeParameterFromExpenseType(long typeParameterId, long expenseTypeId);
//
//	public void grantTypeParameterToExpenseType(long typeParameterId, long expenseTypeId);
//
//	public Page<TypeParameterSendto> findAll(ExpenseTypeParaSpecification spec, Pageable pageable);
//
//	public TypeParameterSendto findByExpenseTypeIdAndTypeParameterId(long expenseTypeId, long typeParameterId);
//}
