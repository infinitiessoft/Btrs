package serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import resources.specification.ExpenseSpecification;
import sendto.ExpenseSendto;
import service.ExpenseService;
import dao.ExpenseDao;
import dao.ExpenseTypeDao;
import dao.ReportDao;
import entity.Expense;
import exceptions.ExpenseNotFoundException;
import exceptions.ExpenseTypeNotFoundException;
import exceptions.ReportNotFoundException;

public class ExpenseServiceImpl implements ExpenseService {

	private ExpenseDao expenseDao;
	private ExpenseTypeDao expenseTypeDao;
	private ReportDao reportDao;

	public ExpenseServiceImpl(ExpenseDao expenseDao, ExpenseTypeDao expenseTypeDao, ReportDao reportDao) {
		this.expenseDao = expenseDao;
		this.expenseTypeDao = expenseTypeDao;
		this.reportDao = reportDao;
	}

	@Transactional("transactionManager")
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
		ret.setTaxAmount(expense.getTaxAmount());
		ret.setComment(expense.getComment());
		ExpenseSendto.Report rpt = new ExpenseSendto.Report();
		rpt.setId(expense.getReport().getId());
		ret.setReport(rpt);
		ExpenseSendto.ExpenseType expType = new ExpenseSendto.ExpenseType();
		expType.setId(expense.getExpenseType().getId());
		ret.setExpenseType(expType);
		return ret;
	}

	@Transactional("transactionManager")
	@Override
	public void delete(long id) {
		try {
			Expense expense = expenseDao.findOne(id);
			if (expense == null) {
				throw new ExpenseNotFoundException(id);
			}
			expenseDao.delete(expense);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			throw new ExpenseNotFoundException(id);
		}
	}

	@Transactional("transactionManager")
	@Override
	public ExpenseSendto save(ExpenseSendto expense) {
		expense.setId(null);
		Expense newEntry = new Expense();
		setUpExpense(expense, newEntry);
		return toExpenseSendto(expenseDao.save(newEntry));
	}

	private void setUpExpense(ExpenseSendto sendto, Expense newEntry) {
		if (sendto.isCommentSet()) {
			newEntry.setComment(sendto.getComment());
		}
		if (sendto.isTotalAmountSet()) {
			newEntry.setTotalAmount(sendto.getTotalAmount());
		}
		if (sendto.isTaxAmountSet()) {
			newEntry.setTaxAmount(sendto.getTaxAmount());
		}
		if (sendto.isReportSet()) {
			if (sendto.getReport().isIdSet()) {
				entity.Report report = reportDao.findOne(sendto.getReport().getId());
				if (report == null) {
					throw new ReportNotFoundException(sendto.getReport().getId());
				}
				newEntry.setReport(report);
			}
		}
		if (sendto.isExpenseTypeSet()) {
			if (sendto.getExpenseType().isIdSet()) {
				entity.ExpenseType expenseType = expenseTypeDao.findOne(sendto.getExpenseType().getId());
				if (expenseType == null) {
					throw new ExpenseTypeNotFoundException(sendto.getExpenseType().getId());
				}
				newEntry.setExpenseType(expenseType);
			}
		}

	}

	@Transactional("transactionManager")
	@Override
	public Page<ExpenseSendto> findAll(ExpenseSpecification spec, Pageable pageable) {
		List<ExpenseSendto> sendto = new ArrayList<ExpenseSendto>();
		Page<Expense> expenses = expenseDao.findAll(spec, pageable);
		for (Expense exp : expenses) {
			sendto.add(toExpenseSendto(exp));
		}
		Page<ExpenseSendto> rets = new PageImpl<ExpenseSendto>(sendto, pageable, expenses.getTotalElements());
		return rets;
	}

	@Transactional("transactionManager")
	@Override
	public ExpenseSendto update(long id, ExpenseSendto updated) {
		Expense exp = expenseDao.findOne(id);
		if (exp == null) {
			throw new ExpenseNotFoundException(id);
		}
		setUpExpense(updated, exp);
		return toExpenseSendto(expenseDao.save(exp));
	}
}
