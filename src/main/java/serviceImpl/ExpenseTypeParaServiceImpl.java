package serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dao.ExpenseTypeDao;
import dao.ExpenseTypeParaDao;
import dao.ParameterValueDao;
import entity.ExpenseType;
import entity.ExpenseTypePara;
import entity.ParameterValue;
import exceptions.ExpenseTypeNotFoundException;
import exceptions.ExpenseTypeParaNotFoundException;
import exceptions.ParameterNotFoundException;
import exceptions.TypeAssignmentNotFoundException;
import sendto.ExpenseTypeParaSendto;
import service.ExpenseTypeParaService;

public class ExpenseTypeParaServiceImpl implements ExpenseTypeParaService {

	private ParameterValueDao parameterDao;
	private ExpenseTypeDao expenseTypeDao;
	private ExpenseTypeParaDao expenseTypeParaDao;

	public ExpenseTypeParaServiceImpl(ParameterValueDao parameterDao, ExpenseTypeDao expenseTypeDao,
			ExpenseTypeParaDao expenseTypeParaDao) {
		this.parameterDao = parameterDao;
		this.expenseTypeDao = expenseTypeDao;
		this.expenseTypeParaDao = expenseTypeParaDao;
	}

	@Override
	public ExpenseTypeParaSendto retrieve(long id) {
		ExpenseTypePara expenseTypePara = expenseTypeParaDao.findOne(id);
		if (expenseTypePara == null) {
			throw new ExpenseTypeParaNotFoundException(id);
		}
		return toExpenseTypeParaSendto(expenseTypePara);
	}

	private ExpenseTypeParaSendto toExpenseTypeParaSendto(ExpenseTypePara expenseTypePara) {
		ExpenseTypeParaSendto ret = new ExpenseTypeParaSendto();
		ret.setId(expenseTypePara.getId());
		return ret;
	}

	@Override
	public void delete(long id) {
		expenseTypeParaDao.delete(id);
	}

	@Override
	public ExpenseTypeParaSendto save(ExpenseTypeParaSendto expenseTypePara) {
		expenseTypePara.setId(null);
		ExpenseTypePara typePara = new ExpenseTypePara();
		typePara = expenseTypeParaDao.save(typePara);
		return toExpenseTypeParaSendto(typePara);
	}

	@Override
	public ExpenseTypeParaSendto update(long id) {
		ExpenseTypePara typePara = expenseTypeParaDao.findOne(id);
		if (typePara == null) {
			throw new ExpenseTypeParaNotFoundException(id);
		}
		return toExpenseTypeParaSendto(expenseTypeParaDao.save(typePara));
	}

	@Override
	public void revokeExpenseTypeFromParameterValue(long expense_id, long parameterValue_id) {
		ExpenseTypePara expenseTypePara = expenseTypeParaDao.findByExpenseTypeIdAndParameterValueId(parameterValue_id,
				expense_id);
		if (expenseTypePara == null) {
			throw new TypeAssignmentNotFoundException(parameterValue_id, expense_id);
		}
		expenseTypeParaDao.delete(expenseTypePara);
	}

	@Override
	public Collection<ExpenseTypeParaSendto> findAll() {
		List<ExpenseTypeParaSendto> sendto = new ArrayList<ExpenseTypeParaSendto>();
		for (ExpenseTypePara typePara : expenseTypeParaDao.findAll()) {
			sendto.add(toExpenseTypeParaSendto(typePara));
		}
		return sendto;
	}

	@Override
	public ExpenseTypeParaSendto findByExpenseTypeIdAndParameterValueId(long expense_id, long parameterValue_id) {
		ExpenseTypePara expenseTypePara = expenseTypeParaDao.findByExpenseTypeIdAndParameterValueId(parameterValue_id,
				expense_id);
		if (expenseTypePara == null) {
			throw new TypeAssignmentNotFoundException(parameterValue_id, expense_id);
		}
		return ExpenseTypeServiceImpl.toExpenseTypeSendto(expenseTypePara);

	}

	@Override
	public void grantExpenseTypeToParameterValue(long parameterValue_id, long expense_id) {
		ExpenseType expenseType = expenseTypeDao.findOne(expense_id);
		if (expenseType == null) {
			throw new ExpenseTypeNotFoundException(expense_id);
		}
		ParameterValue typeParameter = parameterDao.findOne(parameterValue_id);
		if (typeParameter == null) {
			throw new ParameterNotFoundException(parameterValue_id);
		}
		ExpenseTypePara expenseTypePara = new ExpenseTypePara();
		expenseTypePara.setExpenseType(expenseType);
		expenseTypePara.setParameterValue(typeParameter);
		expenseTypeParaDao.save(expenseTypePara);

	}

}
