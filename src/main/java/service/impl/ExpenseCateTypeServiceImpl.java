package service.impl;
//package serviceImpl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//
//import dao.ExpenseCateTypeDao;
//import dao.ExpenseCategoryDao;
//import dao.ExpenseTypeDao;
//import entity.ExpenseCateType;
//import entity.ExpenseCategory;
//import entity.ExpenseType;
//import exceptions.ExpenseCategoryNotFoundException;
//import exceptions.ExpenseTypeAssignmentNotFoundException;
//import exceptions.ExpenseTypeNotFoundException;
//import resources.specification.ExpenseCateTypeSpecification;
//import sendto.ExpenseTypeSendto;
//import service.ExpenseCateTypeService;
//
//public class ExpenseCateTypeServiceImpl implements ExpenseCateTypeService {
//
//	private ExpenseCategoryDao expenseCategoryDao;
//	private ExpenseTypeDao expenseTypeDao;
//	private ExpenseCateTypeDao expenseCateTypeDao;
//
//	public ExpenseCateTypeServiceImpl(ExpenseCategoryDao expenseCategoryDao, ExpenseTypeDao expenseTypeDao,
//			ExpenseCateTypeDao expenseCateTypeDao) {
//		this.expenseCategoryDao = expenseCategoryDao;
//		this.expenseTypeDao = expenseTypeDao;
//		this.expenseCateTypeDao = expenseCateTypeDao;
//	}
//
//	@Override
//	public void revokeExpenseTypeFromExpenseCategory(long expenseCategoryId, long expenseTypeId) {
//		ExpenseCateType expenseCateType = expenseCateTypeDao.findByExpenseCategoryIdAndExpenseTypeId(expenseCategoryId,
//				expenseTypeId);
//		if (expenseCateType == null) {
//			throw new ExpenseTypeAssignmentNotFoundException(expenseCategoryId, expenseTypeId);
//		}
//		expenseCateTypeDao.delete(expenseCateType);
//	}
//
//	@Override
//	public Page<ExpenseTypeSendto> findAll(ExpenseCateTypeSpecification spec, Pageable pageable) {
//		List<ExpenseTypeSendto> sendto = new ArrayList<ExpenseTypeSendto>();
//		Page<ExpenseCateType> expenseCateTypes = expenseCateTypeDao.findAll(spec, pageable);
//		for (ExpenseCateType expense : expenseCateTypes) {
//			ExpenseType expenseType = expense.getExpenseType();
//			sendto.add(ExpenseTypeServiceImpl.toExpenseTypeSendto(expenseType));
//		}
//		Page<ExpenseTypeSendto> rets = new PageImpl<ExpenseTypeSendto>(sendto, pageable,
//				expenseCateTypes.getTotalElements());
//		return rets;
//	}
//
//	@Override
//	public ExpenseTypeSendto findByExpenseCategoryIdAndExpenseTypeId(long expenseCategoryId, long expenseTypeId) {
//		ExpenseCateType expenseCateType = expenseCateTypeDao.findByExpenseCategoryIdAndExpenseTypeId(expenseCategoryId,
//				expenseTypeId);
//		if (expenseCateType == null) {
//			throw new ExpenseTypeAssignmentNotFoundException(expenseCategoryId, expenseTypeId);
//		}
//		return ExpenseTypeServiceImpl.toExpenseTypeSendto(expenseCateType.getExpenseType());
//	}
//
//	@Override
//	public void grantExpenseTypeToExpenseCategory(long expenseCategoryId, long expenseTypeId) {
//		ExpenseCategory expenseCategory = expenseCategoryDao.findOne(expenseCategoryId);
//		if (expenseCategory == null) {
//			throw new ExpenseCategoryNotFoundException(expenseCategoryId);
//		}
//		ExpenseType expenseType = expenseTypeDao.findOne(expenseTypeId);
//		if (expenseType == null) {
//			throw new ExpenseTypeNotFoundException(expenseTypeId);
//		}
//		ExpenseCateType expenseCateType = new ExpenseCateType();
//		expenseCateType.setExpenseCategory(expenseCategory);
//		expenseCateType.setExpenseType(expenseType);
//		expenseCateTypeDao.save(expenseCateType);
//	}
//
//}
