package serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dao.ExpenseCateTypeDao;
import dao.ExpenseCategoryDao;
import dao.ExpenseTypeDao;
import entity.ExpenseCateType;
import entity.ExpenseCategory;
import entity.ExpenseType;
import exceptions.ExpenseAssignmentNotFoundException;
import exceptions.ExpenseCateTypeNotFoundException;
import exceptions.ExpenseCategoryNotFoundException;
import exceptions.ExpenseTypeNotFoundException;
import sendto.ExpenseCateTypeSendto;
import service.ExpenseCateTypeService;

public class ExpenseCateTypeServiceImpl implements ExpenseCateTypeService {

	private ExpenseCategoryDao expenseCategoryDao;
	private ExpenseTypeDao expenseTypeDao;
	private ExpenseCateTypeDao expenseCateTypeDao;

	public ExpenseCateTypeServiceImpl(ExpenseCategoryDao expenseCategoryDao, ExpenseTypeDao expenseTypeDao,
			ExpenseCateTypeDao expenseCateTypeDao) {
		this.expenseCategoryDao = expenseCategoryDao;
		this.expenseTypeDao = expenseTypeDao;
		this.expenseCateTypeDao = expenseCateTypeDao;
	}

	@Override
	public ExpenseCateTypeSendto retrieve(long id) {
		ExpenseCateType expenseCateType = expenseCateTypeDao.findOne(id);
		if (expenseCateType == null) {
			throw new ExpenseCateTypeNotFoundException(id);
		}
		return toExpenseCateTypeSendto(expenseCateType);
	}

	public static ExpenseCateTypeSendto toExpenseCateTypeSendto(ExpenseCateType expenseCateType) {

		return null;
	}

	@Override
	public void delete(long id) {
		expenseCateTypeDao.delete(id);

	}

	@Override
	public ExpenseCateTypeSendto save(ExpenseCateTypeSendto expenseCateType) {
		expenseCateType.setId(null);
		ExpenseCateType cateType = new ExpenseCateType();
		cateType = expenseCateTypeDao.save(cateType);
		return toExpenseCateTypeSendto(cateType);
	}

	@Override
	public ExpenseCateTypeSendto update(long id, ExpenseCateTypeSendto expenseCateType) {
		ExpenseCateType cateType = expenseCateTypeDao.findOne(id);
		if (cateType == null) {
			throw new ExpenseCateTypeNotFoundException(id);
		}
		return toExpenseCateTypeSendto(expenseCateTypeDao.save(expenseCateType));
	}

	@Override
	public void revokeCategoryFromType(long categoryId, long typeId) {
		ExpenseCateType expenseCateType = expenseCateTypeDao.findByEmployeeIdAndRoleId(categoryId, typeId);
		if (expenseCateType == null) {
			throw new ExpenseAssignmentNotFoundException(categoryId, typeId);
		}
		expenseCateTypeDao.delete(expenseCateType);

	}

	@Override
	public Collection<ExpenseCateTypeSendto> findAll() {
		List<ExpenseCateTypeSendto> sendto = new ArrayList<ExpenseCateTypeSendto>();
		Collection<ExpenseCateType> expenseCateType = expenseCateTypeDao.findAll();
		for (ExpenseCateType expCateType : expenseCateType) {
			sendto.add(toExpenseCateTypeSendto(expCateType));
		}
		return sendto;
	}

	@Override
	public ExpenseCateTypeSendto findByCategoryIdAndTypeId(long categoryId, long typeId) {
		ExpenseCateType expenseCateType = expenseCateTypeDao.findByEmployeeIdAndRoleId(categoryId, typeId);
		if (expenseCateType == null) {
			throw new ExpenseAssignmentNotFoundException(categoryId, typeId);
		}
		return ExpenseCategoryServiceImpl.toExpenseCategorySendto(expenseCateType);
	}

	@Override
	public void grantCategoryToType(long categoryId, long typeId) {
		ExpenseCategory expenseCategory = expenseCategoryDao.findOne(categoryId);
		if (expenseCategory == null) {
			throw new ExpenseCategoryNotFoundException(categoryId);
		}
		ExpenseType expenseType = expenseTypeDao.findOne(typeId);
		if (expenseType == null) {
			throw new ExpenseTypeNotFoundException(typeId);
		}
		ExpenseCateType expenseCateType = new ExpenseCateType();
		expenseCateType.setExpenseCategory(expenseCategory);
		expenseCateType.setExpenseType(expenseType);
		expenseCateTypeDao.save(expenseCateType);
	}

}
