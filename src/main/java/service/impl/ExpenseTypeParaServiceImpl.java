//package service.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//
//import dao.ExpenseTypeDao;
//import dao.ExpenseTypeParaDao;
//import dao.TypeParameterDao;
//import entity.ExpenseType;
//import entity.ExpenseTypePara;
//import entity.TypeParameter;
//import exceptions.ExpenseTypeNotFoundException;
//import exceptions.TypeParameterAssignmentNotFoundException;
//import exceptions.TypeParameterNotFoundException;
//import resources.specification.ExpenseTypeParaSpecification;
//import sendto.TypeParameterSendto;
//import service.ExpenseTypeParaService;
//
//public class ExpenseTypeParaServiceImpl implements ExpenseTypeParaService {
//
//	private ExpenseTypeParaDao expenseTypeParaDao;
//	private ExpenseTypeDao expenseTypeDao;
//	private TypeParameterDao typeParameterDao;
//
//	public ExpenseTypeParaServiceImpl(ExpenseTypeDao expenseTypeDao, TypeParameterDao typeParameterDao,
//			ExpenseTypeParaDao expenseTypeParaDao) {
//		this.expenseTypeDao = expenseTypeDao;
//		this.typeParameterDao = typeParameterDao;
//		this.expenseTypeParaDao = expenseTypeParaDao;
//	}
//
//	@Override
//	public void revokeTypeParameterFromExpenseType(long expenseTypeId, long typeParameterId) {
//		ExpenseTypePara expenseTypePara = expenseTypeParaDao.findByExpenseTypeIdAndTypeParameterId(expenseTypeId,
//				typeParameterId);
//		if (expenseTypePara == null) {
//			throw new TypeParameterAssignmentNotFoundException(expenseTypeId, typeParameterId);
//		}
//		expenseTypeParaDao.delete(expenseTypePara);
//	}
//
//	@Override
//	public Page<TypeParameterSendto> findAll(ExpenseTypeParaSpecification spec, Pageable pageable) {
//		List<TypeParameterSendto> sendto = new ArrayList<TypeParameterSendto>();
//		Page<ExpenseTypePara> expenseTypeParas = expenseTypeParaDao.findAll(spec, pageable);
//		for (ExpenseTypePara expense : expenseTypeParas) {
//			TypeParameter typeParameter = expense.getTypeParameter();
//			sendto.add(TypeParameterServiceImpl.toTypeParameterSendto(typeParameter));
//		}
//		Page<TypeParameterSendto> rets = new PageImpl<TypeParameterSendto>(sendto, pageable,
//				expenseTypeParas.getTotalElements());
//		return rets;
//	}
//
//	@Override
//	public TypeParameterSendto findByExpenseTypeIdAndTypeParameterId(long expenseTypeId, long typeParameterId) {
//		ExpenseTypePara expenseTypePara = expenseTypeParaDao.findByExpenseTypeIdAndTypeParameterId(expenseTypeId,
//				typeParameterId);
//		if (expenseTypePara == null) {
//			throw new TypeParameterAssignmentNotFoundException(expenseTypeId, typeParameterId);
//		}
//		return TypeParameterServiceImpl.toTypeParameterSendto(expenseTypePara.getTypeParameter());
//	}
//
//	@Override
//	public void grantTypeParameterToExpenseType(long expenseTypeId, long typeParameterId) {
//		ExpenseType expenseType = expenseTypeDao.findOne(expenseTypeId);
//		if (expenseType == null) {
//			throw new ExpenseTypeNotFoundException(expenseTypeId);
//		}
//		TypeParameter typeParameter = typeParameterDao.findOne(typeParameterId);
//		if (typeParameter == null) {
//			throw new TypeParameterNotFoundException(typeParameterId);
//		}
//		ExpenseTypePara expenseTypePara = new ExpenseTypePara();
//		expenseTypePara.setExpenseType(expenseType);
//		expenseTypePara.setTypeParameter(typeParameter);
//		expenseTypeParaDao.save(expenseTypePara);
//	}
//
//}
