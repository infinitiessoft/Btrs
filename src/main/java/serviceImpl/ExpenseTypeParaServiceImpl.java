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
	public ExpenseTypeParaSendto update(long id, ExpenseTypeParaSendto expenseTypePara) {
		ExpenseTypePara typePara = expenseTypeParaDao.findOne(id);
		if (typePara == null) {
			throw new ExpenseTypeParaNotFoundException(id);
		}
		return toExpenseTypeParaSendto(expenseTypeParaDao.save(expenseTypePara));
	}

	@Override
	public void revokeTypeFromParameter(long typeId, long parameterId) {
		ExpenseTypePara expenseTypePara = expenseTypeParaDao.findByTypeIdAndParameterId(typeId, parameterId);
		if (expenseTypePara == null) {
			throw new TypeAssignmentNotFoundException(parameterId, typeId);
		}
		expenseTypeParaDao.delete(expenseTypePara);
	}

	@Override
	public Collection<ExpenseTypeParaSendto> findAll() {
		List<ExpenseTypeParaSendto> sendto = new ArrayList<ExpenseTypeParaSendto>();
		Collection<ExpenseTypePara> expenseTypePara = expenseTypeParaDao.findAll();
		for (ExpenseTypePara typePara : expenseTypePara) {
			sendto.add(toExpenseTypeParaSendto(typePara));
		}
		return sendto;
	}

	@Override
	public ExpenseTypeParaSendto findByTypeIdAndParameterId(long typeId, long parameterId) {
		ExpenseTypePara expenseTypePara = expenseTypeParaDao.findByTypeIdAndParameterId(parameterId, typeId);
		if (expenseTypePara == null) {
			throw new TypeAssignmentNotFoundException(parameterId, typeId);
		}
		return ExpenseTypeServiceImpl.toExpenseTypeSendto(expenseTypePara);

	}

	@Override
	public void grantTypeToParameter(long typeId, long parameterId) {
		ExpenseType expenseType = expenseTypeDao.findOne(typeId);
		if (expenseType == null) {
			throw new ExpenseTypeNotFoundException(typeId);
		}
		ParameterValue typeParameter = parameterDao.findOne(parameterId);
		if (typeParameter == null) {
			throw new ParameterNotFoundException(parameterId);
		}
		ExpenseTypePara expenseTypePara = new ExpenseTypePara();
		expenseTypePara.setExpenseType(expenseType);
		expenseTypePara.setTypeParameter(typeParameter);
		expenseTypeParaDao.save(expenseTypePara);

	}

}
