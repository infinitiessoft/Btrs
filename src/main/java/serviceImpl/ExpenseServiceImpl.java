package serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dao.ExpenseDao;
import entity.Expense;
import exceptions.ExpenseNotFoundException;
import sendto.ExpenseSendto;
import service.ExpenseService;

public class ExpenseServiceImpl implements ExpenseService {

	private ExpenseDao expenseDao;

	public ExpenseServiceImpl(ExpenseDao expenseDao) {
		this.expenseDao = expenseDao;
	}

	@Override
	public ExpenseSendto retrieve(long id) {
		Expense expense = expenseDao.findOne(id);
		if (expense == null) {
			throw new ExpenseNotFoundException(id);
		}
		return toExpenseSendto(expense);
	}

	private ExpenseSendto toExpenseSendto(Expense expense) {
		ExpenseSendto ret = new ExpenseSendto();
		ret.setId(expense.getId());
		ret.setTotalAmount(expense.getTotalAmount());
		ret.setReport(expense.getReport());
		ret.setTaxAmount(expense.getTaxAmount());
		ret.setComment(expense.getComment());
		return ret;
	}

	@Override
	public void delete(long id) {
		expenseDao.delete(id);

	}

	@Override
	public ExpenseSendto save(ExpenseSendto expense) {
		expense.setId(null);
		Expense exp = new Expense();
		exp = expenseDao.save(exp);
		return toExpenseSendto(exp);
	}

	@Override
	public Collection<ExpenseSendto> findAll() {
		List<ExpenseSendto> sendto = new ArrayList<ExpenseSendto>();
		for (Expense exp : expenseDao.findAll()) {
			sendto.add(toExpenseSendto(exp));
		}
		return sendto;
	}

	@Override
	public ExpenseSendto update(long id) {
		Expense exp = expenseDao.findOne(id);
		if (exp == null) {
			throw new ExpenseNotFoundException(id);
		}
		return toExpenseSendto(expenseDao.save(exp));
	}
}
