package serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

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
		ret.setName_key(expenseCategory.getName_key());
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
		setUpExpenseCategory(expenseCategory, expCat);
		expCat = expenseCategoryDao.save(expCat);
		return toExpenseCategorySendto(expCat);
	}

	@Override
	public Page<ExpenseCategorySendto> findAll(Specification<ExpenseCategory> spec, Pageable pageable) {
		List<ExpenseCategorySendto> sendtos = new ArrayList<ExpenseCategorySendto>();
		Page<ExpenseCategory> expCat = expenseCategoryDao.findAll(spec, pageable);
		for (ExpenseCategory expenseCategory : expCat) {
			sendtos.add(toExpenseCategorySendto(expenseCategory));
		}
		Page<ExpenseCategorySendto> rets = new PageImpl<ExpenseCategorySendto>(sendtos, pageable,
				expCat.getTotalElements());
		return rets;
	}

	@Override
	public ExpenseCategorySendto update(long id, ExpenseCategorySendto updated) {
		ExpenseCategory expCat = expenseCategoryDao.findOne(id);
		if (expCat == null) {
			throw new ExpenseCategoryNotFoundException(id);
		}
		setUpExpenseCategory(updated, expCat);
		return toExpenseCategorySendto(expenseCategoryDao.save(expCat));

	}

	private void setUpExpenseCategory(ExpenseCategorySendto sendto, ExpenseCategory newEntry) {
		if (sendto.isName_keySet()) {
			newEntry.setName_key(sendto.getName_key());
		}
		if (sendto.isCodeSet()) {
			newEntry.setCode(sendto.getCode());
		}
	}

}
