package serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dao.ExpenseTypeDao;
import entity.ExpenseType;
import entity.ExpenseTypePara;
import exceptions.ExpenseTypeNotFoundException;
import sendto.ExpenseTypeParaSendto;
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

	private ExpenseTypeSendto toExpenseTypeSendto(ExpenseType expenseType) {
		ExpenseTypeSendto ret = new ExpenseTypeSendto();
		ret.setId(expenseType.getId());
		ret.setTaxPercent(expenseType.getTaxPercent());
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
		type = expenseTypeDao.save(type);
		return toExpenseTypeSendto(type);
	}

	@Override
	public Collection<ExpenseTypeSendto> findAll() {
		List<ExpenseTypeSendto> sendto = new ArrayList<ExpenseTypeSendto>();
		Collection<ExpenseType> expenseType = expenseTypeDao.findAll();
		for (ExpenseType type : expenseType) {
			sendto.add(toExpenseTypeSendto(type));
		}
		return sendto;
	}

	@Override
	public ExpenseTypeSendto update(long id, ExpenseTypeSendto expenseType) {
		ExpenseType type = expenseTypeDao.findOne(id);
		if (type == null) {
			throw new ExpenseTypeNotFoundException(id);
		}
		return toExpenseTypeSendto(expenseTypeDao.save(expenseType));
	}

	public static ExpenseTypeParaSendto toExpenseTypeSendto(ExpenseTypePara expenseTypePara) {
		// TODO Auto-generated method stub
		return null;
	}

}
