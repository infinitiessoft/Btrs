package serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dao.ExpenseCategoryDao;
import entity.ExpenseCategory;
import exceptions.ExpenseCategoryNotFoundException;
import sendto.ExpenseCategorySendto;
import service.ExpenseCategoryService;

public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {

	private ExpenseCategoryDao expenseCategoryDao;

	public ExpenseCategoryServiceImpl(ExpenseCategoryDao expenseCategoryDao) {
		this.expenseCategoryDao = expenseCategoryDao;
	}

	@Override
	public ExpenseCategorySendto retrieve(long id) {
		ExpenseCategory expenseCategory = expenseCategoryDao.findOne(id);
		if (expenseCategory == null) {
			throw new ExpenseCategoryNotFoundException(id);
		}
		return toExpenseCategorySendto(expenseCategory);
	}

	public static ExpenseCategorySendto toExpenseCategorySendto(ExpenseCategory expenseCategory) {
		ExpenseCategorySendto ret = new ExpenseCategorySendto();
		ret.setId(expenseCategory.getId());
		ret.setName(expenseCategory.getName());
		ret.setCode(expenseCategory.getCode());
		return ret;
	}

	@Override
	public void delete(long id) {
		expenseCategoryDao.delete(id);
	}

	@Override
	public ExpenseCategorySendto save(ExpenseCategorySendto expenseCategory) {
		expenseCategory.setId(null);
		ExpenseCategory expCat = new ExpenseCategory();
		expCat = expenseCategoryDao.save(expCat);
		return toExpenseCategorySendto(expCat);
	}

	@Override
	public Collection<ExpenseCategorySendto> findAll() {
		List<ExpenseCategorySendto> sendto = new ArrayList<ExpenseCategorySendto>();
		for (ExpenseCategory expCategory : expenseCategoryDao.findAll()) {
			sendto.add(toExpenseCategorySendto(expCategory));
		}

		return sendto;
	}

	@Override
	public ExpenseCategorySendto update(long id) {
		ExpenseCategory expCat = expenseCategoryDao.findOne(id);
		if (expCat == null) {
			throw new ExpenseCategoryNotFoundException(id);
		}
		return toExpenseCategorySendto(expenseCategoryDao.save(expCat));
	}

}
