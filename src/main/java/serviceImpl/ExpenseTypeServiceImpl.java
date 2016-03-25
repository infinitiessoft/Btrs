package serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import dao.ExpenseTypeDao;
import entity.ExpenseType;
import exceptions.ExpenseTypeNotFoundException;
import sendto.ExpenseTypeSendto;
import service.ExpenseTypeService;

public class ExpenseTypeServiceImpl implements ExpenseTypeService {

	private ExpenseTypeDao expenseTypeDao;

	public ExpenseTypeServiceImpl(ExpenseTypeDao expenseTypeDao) {
		this.expenseTypeDao = expenseTypeDao;
	}

	@Override
	public ExpenseTypeSendto retrieve(long id) {
		ExpenseType expenseType = expenseTypeDao.findOne(id);
		if (expenseType == null) {
			throw new ExpenseTypeNotFoundException(id);
		}
		return toExpenseTypeSendto(expenseType);
	}

	public static ExpenseTypeSendto toExpenseTypeSendto(ExpenseType expenseType) {
		ExpenseTypeSendto ret = new ExpenseTypeSendto();
		ret.setId(expenseType.getId());
		ret.setTaxPercent(expenseType.getTaxPercent());
		ret.setValue(expenseType.getValue());
		return ret;
	}

	@Override
	public void delete(long id) {
		expenseTypeDao.delete(id);

	}

	@Override
	public ExpenseTypeSendto save(ExpenseTypeSendto expenseType) {
		expenseType.setId(null);
		ExpenseType type = new ExpenseType();
		setUpExpenseType(expenseType, type);
		type = expenseTypeDao.save(type);
		return toExpenseTypeSendto(type);
	}

	@Override
	public Page<ExpenseTypeSendto> findAll(Specification<ExpenseType> spec, Pageable pageable) {
		List<ExpenseTypeSendto> sendto = new ArrayList<ExpenseTypeSendto>();
		Page<ExpenseType> expType = expenseTypeDao.findAll(spec, pageable);
		for (ExpenseType expenseType : expType) {
			sendto.add(toExpenseTypeSendto(expenseType));
		}
		Page<ExpenseTypeSendto> rets = new PageImpl<ExpenseTypeSendto>(sendto, pageable, expType.getTotalElements());
		return rets;
	}

	@Override
	public ExpenseTypeSendto update(long id, ExpenseTypeSendto updated) {
		ExpenseType type = expenseTypeDao.findOne(id);
		if (type == null) {
			throw new ExpenseTypeNotFoundException(id);
		}
		setUpExpenseType(updated, type);
		return toExpenseTypeSendto(expenseTypeDao.save(type));
	}

	private void setUpExpenseType(ExpenseTypeSendto sendto, ExpenseType newEntry) {
		if (sendto.isTaxPercentSet()) {
			newEntry.setTaxPercent(sendto.getTaxPercent());
		}
		if (sendto.isValueSet()) {
			newEntry.setValue(sendto.getValue());
		}

	}

}
