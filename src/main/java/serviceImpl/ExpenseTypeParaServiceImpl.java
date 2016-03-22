package serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import dao.ExpenseTypeDao;
import dao.ExpenseTypeParaDao;
import dao.ParameterValueDao;
import entity.ExpenseType;
import entity.ExpenseTypePara;
import entity.ParameterValue;
import exceptions.ExpenseTypeNotFoundException;
import exceptions.ParameterNotFoundException;
import exceptions.ParameterValueAssignmentNotFoundException;
import resources.specification.ExpenseTypeParaSpecification;
import sendto.ParameterValueSendto;
import service.ExpenseTypeParaService;

public class ExpenseTypeParaServiceImpl implements ExpenseTypeParaService {

	private ExpenseTypeDao expenseTypeDao;
	private ParameterValueDao parameterValueDao;
	private ExpenseTypeParaDao expenseTypeParaDao;

	public ExpenseTypeParaServiceImpl(ExpenseTypeDao expenseTypeDao, ParameterValueDao parameterValueDao,
			ExpenseTypeParaDao expenseTypeParaDao) {
		this.expenseTypeDao = expenseTypeDao;
		this.parameterValueDao = parameterValueDao;
		this.expenseTypeParaDao = expenseTypeParaDao;
	}

	@Override
	public Page<ParameterValueSendto> findAll(ExpenseTypeParaSpecification spec, Pageable pageable) {
		List<ParameterValueSendto> sendto = new ArrayList<ParameterValueSendto>();
		Page<ExpenseTypePara> expenseTypes = expenseTypeParaDao.findAll(spec, pageable);
		for (ExpenseTypePara expenseType : expenseTypes) {
			ParameterValue parameterValue = expenseType.getParameterValue();
			sendto.add(ParameterValueServiceImpl.toParameterValueSendto(parameterValue));
		}
		Page<ParameterValueSendto> rets = new PageImpl<ParameterValueSendto>(sendto, pageable,
				expenseTypes.getTotalElements());
		return rets;
	}

	@Override
	public ParameterValueSendto findByExpenseTypeIdAndParameterValueId(long expenseTypeId, long parameterValueId) {
		ExpenseTypePara expenseTypePara = expenseTypeParaDao.findByExpenseTypeIdAndParameterValueId(expenseTypeId,
				parameterValueId);
		if (expenseTypePara == null) {
			throw new ParameterValueAssignmentNotFoundException(expenseTypeId, parameterValueId);
		}
		return ParameterValueServiceImpl.toParameterValueSendto(expenseTypePara.getParameterValue());
	}

	@Override
	public void revokeParameterValueFromExpenseType(long expenseTypeId, long parameterValueId) {
		ExpenseTypePara expenseTypePara = expenseTypeParaDao.findByExpenseTypeIdAndParameterValueId(expenseTypeId,
				parameterValueId);
		if (expenseTypePara == null) {
			throw new ParameterValueAssignmentNotFoundException(expenseTypeId, parameterValueId);
		}
		expenseTypeParaDao.delete(expenseTypePara);
	}

	@Override
	public void grantParameterValueToExpenseType(long expenseTypeId, long parameterValueId) {
		ExpenseType expenseType = expenseTypeDao.findOne(expenseTypeId);
		if (expenseType == null) {
			throw new ExpenseTypeNotFoundException(expenseTypeId);
		}
		ParameterValue parameterValue = parameterValueDao.findOne(parameterValueId);
		if (parameterValue == null) {
			throw new ParameterNotFoundException(parameterValueId);
		}
		ExpenseTypePara expenseTypePara = new ExpenseTypePara();
		expenseTypePara.setExpenseType(expenseType);
		expenseTypePara.setParameterValue(parameterValue);
		expenseTypeParaDao.save(expenseTypePara);
	}
}