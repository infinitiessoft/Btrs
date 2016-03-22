package serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import dao.ExpenseDao;
import dao.ParameterValueDao;
import dao.TypeParameterDao;
import entity.ParameterValue;
import exceptions.ExpenseNotFoundException;
import exceptions.ParameterNotFoundException;
import resources.specification.ParameterValueSpecification;
import sendto.ParameterValueSendto;
import sendto.ParameterValueSendto.TypeParameter;
import service.ParameterValueService;

public class ParameterValueServiceImpl implements ParameterValueService {

	private ParameterValueDao parameterValueDao;
	private TypeParameterDao typeParameterDao;
	private ExpenseDao expenseDao;

	public ParameterValueServiceImpl(ParameterValueDao parameterValueDao, TypeParameterDao typeParameterDao,
			ExpenseDao expenseDao) {
		this.parameterValueDao = parameterValueDao;
		this.typeParameterDao = typeParameterDao;
		this.expenseDao = expenseDao;
	}

	@Transactional
	@Override
	public ParameterValueSendto retrieve(long id) {
		ParameterValue parameterValue = parameterValueDao.findOne(id);
		if (parameterValue == null) {
			throw new ParameterNotFoundException();
		}
		return toParameterValueSendto(parameterValue);
	}

	@Transactional
	@Override
	public ParameterValueSendto retrieve(ParameterValueSpecification spec) {
		ParameterValue parameterValue = parameterValueDao.findOne(spec);
		if (parameterValue == null) {
			throw new ParameterNotFoundException();
		}
		return toParameterValueSendto(parameterValue);
	}

	public static ParameterValueSendto toParameterValueSendto(ParameterValue parameterValue) {
		ParameterValueSendto ret = new ParameterValueSendto();
		ret.setId(parameterValue.getId());
		ret.setValue(parameterValue.getValue());

		ParameterValueSendto.Expense exp = new ParameterValueSendto.Expense();
		exp.setId(parameterValue.getExpense().getId());
		ret.setExpense(exp);

		entity.TypeParameter type = parameterValue.getTypeParameter();
		TypeParameter typeParameter = new ParameterValueSendto.TypeParameter();
		typeParameter.setId(type.getId());
		ret.setTypeParameter(typeParameter);
		return ret;
	}

	@Transactional
	@Override
	public void delete(long id) {
		try {
			parameterValueDao.delete(id);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			throw new ParameterNotFoundException(id);
		}

	}

	@Transactional
	@Override
	public ParameterValueSendto save(ParameterValueSendto parameterValue) {
		parameterValue.setId(null);
		ParameterValue newEntry = new ParameterValue();
		if (parameterValue.getValue() == null) {
			parameterValue.setValue("value");
		}
		setUpParameterValue(parameterValue, newEntry);
		return toParameterValueSendto(parameterValueDao.save(newEntry));
	}

	@Transactional
	@Override
	public Page<ParameterValueSendto> findAll(ParameterValueSpecification spec, Pageable pageable) {
		List<ParameterValueSendto> sendto = new ArrayList<ParameterValueSendto>();
		Page<ParameterValue> parameters = parameterValueDao.findAll(spec, pageable);
		for (ParameterValue parameterValue : parameters) {
			sendto.add(toParameterValueSendto(parameterValue));
		}
		Page<ParameterValueSendto> rets = new PageImpl<ParameterValueSendto>(sendto, pageable,
				parameters.getTotalElements());
		return rets;
	}

	@Transactional
	@Override
	public ParameterValueSendto update(long id, ParameterValueSendto updated) {
		ParameterValue parameterValue = parameterValueDao.findOne(id);
		if (parameterValue == null) {
			throw new ParameterNotFoundException(id);
		}
		if (updated.isValueSet()) {
			parameterValue.setValue(updated.getValue());
		}
		return toParameterValueSendto(parameterValueDao.save(parameterValue));

	}

	private void setUpParameterValue(ParameterValueSendto sendto, ParameterValue newEntry) {
		if (sendto.isExpenseSet()) {
			if (sendto.getExpense().isIdSet()) {
				entity.Expense exp = expenseDao.findOne(sendto.getExpense().getId());
				if (exp == null) {
					throw new ExpenseNotFoundException(sendto.getExpense().getId());
				}

				newEntry.setExpense(exp);
			}
		}
		if (sendto.isTypeParameterSet()) {
			if (sendto.getTypeParameter().isIdSet()) {
				entity.TypeParameter typeParameter = typeParameterDao.findOne(sendto.getTypeParameter().getId());
				if (typeParameter == null) {
					throw new ExpenseNotFoundException(sendto.getExpense().getId());
				}

				newEntry.setTypeParameter(typeParameter);
			}
		}
		if (sendto.isValueSet()) {
			newEntry.setValue(sendto.getValue());
		}

	}

}
